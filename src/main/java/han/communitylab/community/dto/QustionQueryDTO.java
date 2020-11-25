package han.communitylab.community.dto;

import lombok.Data;

@Data
public class QustionQueryDTO {
    private String search;
    private String tag;
    private Integer page;
    private Integer size;
}
