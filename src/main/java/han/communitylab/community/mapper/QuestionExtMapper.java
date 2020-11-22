package han.communitylab.community.mapper;

import han.communitylab.community.dto.QustionQueryDTO;
import han.communitylab.community.model.Question;

import java.util.List;


public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);

    Integer countBySearch(QustionQueryDTO qustionQueryDTO);

    List<Question> selectBySearch(QustionQueryDTO qustionQueryDTO);
}