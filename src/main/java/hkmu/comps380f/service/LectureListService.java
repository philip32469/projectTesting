package hkmu.comps380f.service;

import hkmu.comps380f.dao.LectureListRepository;
import hkmu.comps380f.model.LectureList;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LectureListService {

    @Resource
    private LectureListRepository LectureListRepo;

    @Transactional
    public List<LectureList> getLectureList() {
        return LectureListRepo.findAll();
    }

    @Transactional
    public String addLectureList(String courseCode,
            String courseName) throws IOException {
        LectureList lectureList = new LectureList();
        lectureList.setCoursecode(courseCode);
        lectureList.setCoursename(courseName);
        LectureList savedLectureList = LectureListRepo.save(lectureList);
        return savedLectureList.getCoursecode();
    }
}
