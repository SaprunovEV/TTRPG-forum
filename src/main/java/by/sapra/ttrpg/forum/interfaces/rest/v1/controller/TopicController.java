package by.sapra.ttrpg.forum.interfaces.rest.v1.controller;

import by.sapra.ttrpg.forum.application.TopicService;
import by.sapra.ttrpg.forum.interfaces.rest.v1.mapper.TopicMapper;
import by.sapra.ttrpg.forum.interfaces.rest.v1.model.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService service;
    private final TopicMapper mapper;

    @GetMapping("/{topicId}")
    public ResponseEntity<TopicResource> findTopiById(
            @PathVariable("topicId") @Valid MassageVariable variable,
            @PageableDefault(size = 50,
                    sort = "dateInfo.createdAt",
                    direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                mapper.entityToResource(
                        service.findTopicById(
                                mapper.variableToQuery(variable, pageable))));
    }

    @PostMapping
    public ResponseEntity<TopicResource> postNewTopic(
            @RequestHeader("X-Auth-UserId") @Valid UserAuthId author,
            @RequestBody @Valid PostTopicPayload payload
    ) {

        TopicResource body = mapper.entityToResource(
                service.addNewTopic(mapper.payloadToCommand(author, payload)));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{topicId}")
                .buildAndExpand(body.topicId())
                .toUri();

        return ResponseEntity.created(uri).body(body);
    }

    @PostMapping("/{topicId}/comments")
    public ResponseEntity<CommentResource> postCommentToTopic(
            @RequestHeader("X-Auth-UserID") @Valid UserAuthId author,
            @PathVariable("topicId") @Valid MassageVariable topicId,
            @RequestBody @Valid PostCommentPayload payload
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.entityToResource(
                        service.commentMassage(
                                mapper.payloadToCommand(author, topicId, payload))));
    }

    @GetMapping("/following/feed")
    public ResponseEntity<PageResource<MessageResource>> getFollowingActivityFeed(
            @RequestHeader("X-Auth-UserId") @Valid UserAuthId subscriber,
            @PageableDefault(size = 50,
                    sort = "dateInfo.createdAt",
                    direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                mapper.entitiesToPage(
                        service.getActivityToSubscriber(
                                mapper.variableToQuery(subscriber, pageable))));
    }

    @GetMapping("owner/feed")
    public ResponseEntity<PageResource<MessageResource>> getOwnerActivityFeed(
            @RequestHeader("X-Auth-UserId") @Valid UserAuthId owner,
            @PageableDefault(size = 50,
                    sort = "dateInfo.createdAt",
                    direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                mapper.entitiesToPage(
                        service.getActivityToOwner(
                                mapper.variableToQuery(owner, pageable))));
    }

    @PutMapping("/{messageId}/like")
    public ResponseEntity<Void> likeMessage(
            @RequestHeader("X-Auth-UserId") @Valid UserAuthId userId,
            @PathVariable("messageId") @Valid MassageVariable variable,
            @RequestBody @Valid LikePayload payload
    ) {

        service.likeMessage(mapper.payloadToCommand(userId,variable, payload));

        return ResponseEntity.noContent().build();
    }
}
