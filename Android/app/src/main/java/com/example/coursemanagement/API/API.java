package com.example.coursemanagement.API;

import com.example.coursemanagement.DataTransferObject.AdministratorRegistration;
import com.example.coursemanagement.DataTransferObject.BatchRegistration;
import com.example.coursemanagement.DataTransferObject.CourseRegistration;
import com.example.coursemanagement.DataTransferObject.HeadmasterRegistration;
import com.example.coursemanagement.DataTransferObject.StudentRegistration;
import com.example.coursemanagement.DataTransferObject.TeacherRegistration;
import com.example.coursemanagement.Entity.Administrator;
import com.example.coursemanagement.Entity.Batch;
import com.example.coursemanagement.Entity.Course;
import com.example.coursemanagement.Entity.Exam;
import com.example.coursemanagement.Entity.Headmaster;
import com.example.coursemanagement.Entity.Login;
import com.example.coursemanagement.Entity.Mark;
import com.example.coursemanagement.Entity.Student;
import com.example.coursemanagement.Entity.Teacher;
import com.example.coursemanagement.Entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {

    // ********************************* Login Functions ********************************* //

    @POST("api/user/login")
    Call<String> userLogin(@Body Login login);

    // ******************************* Administrator Functions ******************************* //

    @GET("api/administrator/listAllUsers")
    Call<List<User>> getAllUsers();

    @GET("api/administrator/listAllAdmins")
    Call<List<Administrator>> getAllAdministrators();

    @GET("api/administrator/listAllHeadmasters")
    Call<List<Headmaster>> getAllHeadmasters();

    @GET("api/administrator/listAllTeachers")
    Call<List<Teacher>> getAllTeachers();

    @GET("api/administrator/listAllStudents")
    Call<List<Student>> getAllStudents();

    @POST("api/user/saveAdministrator")
    Call<String> registerAdministrator(@Body AdministratorRegistration administratorRegistration);

    @POST("api/headmaster/saveHeadmaster")
    Call<String> registerHeadmaster(@Body HeadmasterRegistration headmasterRegistration);

    @POST("api/teacher/saveTeacher")
    Call<String> registerTeacher(@Body TeacherRegistration teacherRegistration);

    @POST("api/student/saveStudent")
    Call<String> registerStudent(@Body StudentRegistration studentRegistration);

    @GET("api/administrator/viewAdministrator/{user_ID}")
    Call<User> getUser(@Path("user_ID") int id);

    @POST("api/administrator/editAdministrator")
    Call<String> updateUser(@Body User user);

    @GET("api/administrator/deleteAdministrator/{user_ID}")
    Call<String> deleteUser(@Path("user_ID") int id);

    @GET("api/headmaster/viewHeadmaster/{user_ID}")
    Call<User> getHeadmaster(@Path("user_ID") int id);

    @POST("api/headmaster/editHeadmaster")
    Call<String> updateHeadmaster(@Body User user);

    @GET("api/headmaster/deleteHeadmaster/{user_ID}")
    Call<String> deleteHeadmaster(@Path("user_ID") int id);

    // ********************************* Headmaster Functions ********************************* //

    @POST("api/headmaster/saveCourse")
    Call<String> registerCourse(@Body CourseRegistration courseRegistration);

    @GET("api/headmaster/viewCourse/{course_ID}")
    Call<Course> getCourse(@Path("course_ID") int id);

    @GET("api/headmaster/listAllCourses")
    Call<List<Course>> getAllCourses();

    @POST("api/headmaster/editCourse")
    Call<String> updateCourse(@Body Course course);

    @GET("api/headmaster/deleteCourse/{course_ID}")
    Call<String> deleteCourse(@Path("course_ID") int id);

    @GET("api/teacher/viewTeacher/{user_ID}")
    Call<User> getTeacher(@Path("user_ID") int id);

    @POST("api/teacher/editTeacher")
    Call<String> updateTeacher(@Body User user);

    @GET("api/teacher/deleteTeacher/{user_ID}")
    Call<String> deleteTeacher(@Path("user_ID") int id);

    @GET("api/student/viewStudent/{user_ID}")
    Call<User> getStudent(@Path("user_ID") int id);

    @POST("api/student/editStudent")
    Call<String> updateStudent(@Body User user);

    @GET("api/student/deleteStudent/{user_ID}")
    Call<String> deleteStudent(@Path("user_ID") int id);

    @GET("api/headmaster/listAllBatches")
    Call<List<Batch>> getAllBatches();

    @POST("api/headmaster/addNewBatch")
    Call<String> registerBatch(@Body BatchRegistration batchRegistration);

    @GET("api/headmaster/viewBatch/{batch_ID}")
    Call<Batch> viewBatch(@Path("batch_ID") int batch_ID);

    @POST("api/headmaster/updateBatch")
    Call<String> updateBatch(@Body Batch batch);

    @GET("api/headmaster/deleteBatch/{batch_ID}")
    Call<String> deleteBatch(@Path("batch_ID") int batch_ID);

    // ********************************* Teacher Functions ********************************* //

    @GET("api/teacher/viewBatchesForTeacher")
    Call<List<Batch>> viewBatchesForTeacher(@Header("account-id") int accountId);

    @GET("api/teacher/viewExamsForTeacher")
    Call<List<Exam>> viewExamsForTeacher(@Header("account-id") int accountId);

    // ********************************* Student Functions ********************************* //

    @GET("api/student/viewMarksForStudent")
    Call<List<Mark>> viewMarksForStudent(@Header("account-id") int accountId);

}
