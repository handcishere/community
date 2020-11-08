package han.communitylab.community.service;

import han.communitylab.community.dto.PaginationDTO;
import han.communitylab.community.dto.QuestionDTO;
import han.communitylab.community.mapper.QuestionMapper;
import han.communitylab.community.mapper.UserMapper;
import han.communitylab.community.model.Question;
import han.communitylab.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO List(Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        Integer totalPage ;
        Integer totalCount = questionMapper.count();

        if(totalCount % size==0){
            totalPage=totalCount / size;
        }else{
            totalPage=totalCount / size +1;
        }

        if(page<1)page=1;
        paginationDTO.setPagination(totalPage,page);
        if(page>paginationDTO.getTotalPage())page=totalPage;
        Integer offset=size*(page-1);
        List<Question> questions=questionMapper.List(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        Integer totalPage ;
        Integer totalCount = questionMapper.countByUserId(userId);

        if(totalCount % size==0){
            totalPage=totalCount / size;
        }else{
            totalPage=totalCount / size +1;
        }

        if(page<1)page=1;
        paginationDTO.setPagination(totalPage,page);
        if(page>paginationDTO.getTotalPage())page=totalPage;

        Integer offset=size*(page-1);
        List<Question> questions=questionMapper.ListByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }
}
