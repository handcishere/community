package han.communitylab.community.dto;

import lombok.Data;

@Data
public class QustionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
