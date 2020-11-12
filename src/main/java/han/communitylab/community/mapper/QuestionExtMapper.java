package han.communitylab.community.mapper;

import han.communitylab.community.model.Question;


public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}