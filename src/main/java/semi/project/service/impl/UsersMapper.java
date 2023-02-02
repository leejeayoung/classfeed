package semi.project.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import semi.project.domain.UsersVo;

@Mapper("usersMapper")
public interface UsersMapper {
	List<UsersVo> selectUserList(UsersVo usersVo) throws Exception;
}
