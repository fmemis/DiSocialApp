package di.uoa.gr.m151.socialapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedReactionId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "feed_post_id")
    private UUID feedPostId;


}
