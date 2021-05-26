package di.uoa.gr.m151.socialapp.DTO;

import di.uoa.gr.m151.socialapp.entity.FeedReaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedPostDTO {
    UUID postId;

    String content;

    //Timestamp postTime;
    Date postTime;

    Collection<FeedReactionDTO> userReactions;

    String username;

   // Integer currentUserReaction;

}