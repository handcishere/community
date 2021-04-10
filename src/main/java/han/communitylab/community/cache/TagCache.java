package han.communitylab.community.cache;

import han.communitylab.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public  class TagCache {
    public static List<TagDTO> get(){
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO program = new TagDTO();
        program.setCategoryName("球类");
        program.setTags(Arrays.asList("篮球","足球","乒乓球","羽毛球","排球"));
        tagDTOS.add(program);

         program = new TagDTO();
        program.setCategoryName("艺术类");
        program.setTags(Arrays.asList("音乐","美术","雕塑","艺体","表演","编导"));
        tagDTOS.add(program);

         program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("cpp","java","python","c#","golang","javascript","c"));
        tagDTOS.add(program);

        program = new TagDTO();
        program.setCategoryName("游戏");
        program.setTags(Arrays.asList("LOL","PUBG","DNF","原神","塞尔达"));
        tagDTOS.add(program);
        
        return tagDTOS;
    }
    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS=get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
