package com.example.course_system.service;

import com.example.course_system.entity.Course;
import com.example.course_system.entity.Selection;
import com.example.course_system.entity.SelectionPK;
import com.example.course_system.entity.Student;
import com.example.course_system.repository.CourseDao;
import com.example.course_system.repository.SelectionDao;
import com.example.course_system.repository.StudentDao;
import com.example.course_system.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CourseSystemServiceImpl implements CourseSystemService{
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SelectionDao selectionDao;


//    ---- COURSE SERVICE ----
    @Override
    public CourseResponse addCourse(CourseRequest courseRequest) {
//        確認課程 ID 是否重複
        if(courseDao.existsById(courseRequest.getCourseID())) {
            return new CourseResponse("課程已存在");
        }

//        打包 Entity
        Course newCourse = new Course(courseRequest.getCourseID(), courseRequest.getCourseName(), courseRequest.getDay(), courseRequest.getStartTime(), courseRequest.getEndTime(), courseRequest.getCredit());

//        確認課程資訊是否正確
        Boolean checkResult = this.checkCourseFormatWithoutID(newCourse);
        if(!checkResult) {
            return new CourseResponse("課程資訊錯誤", newCourse);
        }

//        新增課程
        courseDao.save(newCourse);
        return new CourseResponse("已新增課程", newCourse);
    }

    @Override
    public CourseResponse setCourse(CourseRequest courseRequest) {
//        確認 ID 是否存在
        if(!courseDao.existsById(courseRequest.getCourseID())) {
            return new CourseResponse("課程不存在");
        }

//        取出資料庫中要修改的課程
        Course existCourse = courseDao.findById(courseRequest.getCourseID()).get();

//        重新設定資訊
        String courseID = courseRequest.getCourseID();
        String courseName = StringUtils.hasText(courseRequest.getCourseName())
                ? courseRequest.getCourseName() : existCourse.getCourseName();
        Integer day = !(courseRequest.getDay() == null)
                ? courseRequest.getDay() : existCourse.getDay();
        Integer startTime = !(courseRequest.getStartTime() == null)
                ? courseRequest.getStartTime() : existCourse.getStartTime();
        Integer endTime = !(courseRequest.getEndTime() == null)
                ? courseRequest.getEndTime() : existCourse.getEndTime();
        Integer credit = !(courseRequest.getCredit() == null)
                ? courseRequest.getCredit() : existCourse.getCredit();

//        打包 Entity
        Course setCourse = new Course(courseID, courseName, day, startTime, endTime, credit);

//        確認課程資訊是否正確
        Boolean checkResult = this.checkCourseFormatWithoutID(setCourse);
        if(!checkResult) {
            return new CourseResponse("課程資訊錯誤", setCourse);
        }

//        修改課程資訊
        courseDao.save(setCourse);
        return new CourseResponse("已修改課程", setCourse);
    }

    @Override
    public CourseResponse deleteCourse(CourseRequest courseRequest) {
//        確認 ID 是否存在
        if(!courseDao.existsById(courseRequest.getCourseID())) {
            return new CourseResponse("課程不存在");
        }

//        確認課程是否有學生選修
        if(selectionDao.existsByCourseID(courseRequest.getCourseID())) {
            return new CourseResponse("課程仍有學生選修");
        }

        courseDao.deleteById(courseRequest.getCourseID());

        return new CourseResponse("已刪除課程");
    }

    @Override
    public List<Course> getAllCourse() {
        System.out.println("getAllCourse");
        List<Course> courseList = courseDao.findAll();
        return courseList;
    }


//    ---- STUDENT SERVICE ----
    @Override
    public StudentResponse addStudent(StudentRequest studentRequest) {
//        確認學生 ID 是否存在
        if(studentDao.existsById(studentRequest.getStudentID())) {
            return new StudentResponse("學生 ID 已存在");
        }

//        確認學生姓名是否為空
        if(!StringUtils.hasText(studentRequest.getStudentName())) {
            return new StudentResponse("學生姓名不可為空");
        }

//        新增學生
        Student newStudent = new Student(studentRequest.getStudentID(), studentRequest.getStudentName());

        studentDao.save(newStudent);

        return new StudentResponse("已新增學生", newStudent);
    }

    @Override
    public StudentResponse setStudent(StudentRequest studentRequest) {
//        確認學生 ID 是否存在
        if(!studentDao.existsById(studentRequest.getStudentID())) {
            return new StudentResponse("學生 ID 不存在");
        }

//        確認學生姓名是否為空
        if(!StringUtils.hasText(studentRequest.getStudentName())) {
            return new StudentResponse("學生姓名不可為空");
        }

//        修改學生姓名
        Student setStudent = new Student(studentRequest.getStudentID(), studentRequest.getStudentName());

        studentDao.save(setStudent);

        return new StudentResponse("已修改學生姓名", setStudent);
    }

    @Override
    public StudentResponse deleteStudent(StudentRequest studentRequest) {
//        確認學生 ID 是否存在
        if(!studentDao.existsById(studentRequest.getStudentID())) {
            return new StudentResponse("學生 ID 不存在");
        }

//        確認學生是否有選課
        if(selectionDao.existsByStudentID(studentRequest.getStudentID())) {
            return new StudentResponse("請先將選課退選");
        }

//        刪除學生資料
        studentDao.deleteById(studentRequest.getStudentID());

        return new StudentResponse("已刪除學生資料");
    }


//    ---- SELECTION SERVICE ----
    @Override
    public SelectionResponse addSelection(SelectionRequest selectionRequest) {
//        確認學生ID是否為空
        if(!StringUtils.hasText(selectionRequest.getStudentID())) {
            return new SelectionResponse("請輸入學生ID");
        }
//        確認課程ID是否為空
        if(!StringUtils.hasText(selectionRequest.getCourseID())) {
            return new SelectionResponse("請輸入課程ID");
        }

//        要選課的課程資訊
        Course selectCourse = courseDao.findById(selectionRequest.getCourseID()).get();
//        已選課程列表
        List<Selection> selectedList = selectionDao.findByStudentID(selectionRequest.getStudentID());
//        學生學分(預設要選課的學分)
        int studentCredit = selectCourse.getCredit();

//        確認學生學分上限 & 衝堂 & 同名課堂
        for(Selection selection : selectedList) {
//            已選課程資訊
            Course selectedCourse = courseDao.findById(selection.getCourseID()).get();

//            確認每位學生修習上限只能10學分
            studentCredit += selectedCourse.getCredit();
            System.out.println("studentCredit: " + studentCredit);
            if(studentCredit >10) {
                return new SelectionResponse("已超過選修學分");
            }


//            確認加選的課程要比對目前已選上的上課星期與時間
//              確認星期幾是否重複
            if(selectCourse.getDay() == selectedCourse.getDay()) {
//                確認時間是否衝堂
                if((selectCourse.getStartTime() > selectedCourse.getStartTime()
                    && selectCourse.getStartTime() < selectedCourse.getEndTime())
                    || (selectCourse.getEndTime() < selectedCourse.getEndTime()
                    && selectCourse.getEndTime() > selectedCourse.getStartTime())) {
                    return new SelectionResponse("時間衝堂");
                }
            }

//            不能重複選修同名稱(不同課程代號)的課程
            if(selectCourse.getCourseName() == selectedCourse.getCourseName()) {
                return new SelectionResponse("不能選修相同課程名稱的課");
            }
        }

//        確認每堂課最多三位學生
//          要選課程的選修列表
        if(selectionDao.countByCourseID(selectionRequest.getCourseID()) >= 3) {
            return new SelectionResponse("已達選修人數上限");
        }

//        新增選修
        Selection newSelection = new Selection(selectionRequest.getStudentID(), selectionRequest.getCourseID());
        selectionDao.save(newSelection);
        return new SelectionResponse("已新增選課", newSelection);
    }

    @Override
    public SelectionResponse deleteSelection(SelectionRequest selectionRequest) {
//        確認學生 ID 是否存在
        if(!StringUtils.hasText(selectionRequest.getStudentID())) {
            return new SelectionResponse("請輸入學生ID");
        }

//        確認課程 ID 是否存在
        if(!StringUtils.hasText(selectionRequest.getCourseID())) {
            return new SelectionResponse("請輸入課程ID");
        }
//        打包 SelectionPK
        SelectionPK deleteSelectionPK =
                new SelectionPK(selectionRequest.getStudentID(), selectionRequest.getCourseID());

//        確認該 PK 是否存在
        if(!selectionDao.existsById(deleteSelectionPK)) {
            return new SelectionResponse("沒有該選課");
        }
//        刪除選課
        selectionDao.deleteById(deleteSelectionPK);
        return new SelectionResponse("已刪除該選課");
    }

    @Override
    public SelectionResponse getSelectionByStudent(SelectionRequest selectionRequest) {
//        確認學生 ID 是否存在
        if(selectionDao.existsByStudentID(selectionRequest.getStudentID())) {
            return new SelectionResponse("學生 ID 不存在");
        }

        var selectionList = selectionDao.findByStudentID(selectionRequest.getStudentID());
        return new SelectionResponse("查詢成功", selectionList);
    }

//    私有方法，驗證課程格式(除了ID)
    private Boolean checkCourseFormatWithoutID(Course course) {
//        確認 name 是否為空
        if(course.getCourseName() == null) {
            return false;
        }

//        確認日期是否介於 1~7 之間
        if(course.getDay() < 0 || course.getDay() > 7) {
            return false;
        }

//        確認小時介於 00~24 之間
        double startHour = Math.floor(course.getStartTime() / 100);
        double endHour = Math.floor(course.getEndTime() / 100);
        if(startHour < 0 || startHour > 24
            || endHour < 0 || endHour > 24){
            return false;
        }

//        確認分介於 00~60 之間
        double startMinute = Math.floor(course.getStartTime() % 100);
        double endMinute = Math.floor(course.getEndTime() % 100);
        if(startMinute < 0 || startMinute > 60
            || endMinute < 0 || endMinute > 60){
            return false;
        }

//        確認結束時間 > 開始時間
        if(!(course.getStartTime() < course.getEndTime())) {
            return false;
        }

//        確認學分是否介於 1~3 之間
        if(course.getCredit() < 1 || course.getCredit() > 3) {
            return false;
        }

        return true;
    }
}
