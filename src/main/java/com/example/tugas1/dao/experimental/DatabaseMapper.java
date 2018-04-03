package com.example.tugas1.dao.experimental;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DatabaseMapper<E> {
	
	E selectData(@Param("table") String table, @Param("id") String id);
	List<E> selectAllData(@Param("table") String table);
	void insertData(@Param("table") String table, @Param("values") String values);
	void updateData(@Param("table") String table, @Param("set") String set);
	void deleteData(@Param("table") String table, @Param("id") String id);
}