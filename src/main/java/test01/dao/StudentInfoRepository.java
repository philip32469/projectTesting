package test01.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test01.model.StudentInfo;

@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer> {
}
