package han.communitylab.community.mapper;

import han.communitylab.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name}," +
            "#{accountId},#{token},#{gmtcreate},#{gmtModified})")
    void insert(User user);
}
