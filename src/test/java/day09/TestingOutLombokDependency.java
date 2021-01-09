package day09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Department;
import testbase.HR_ORDS_TestBase;
import utility.DB_Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestingOutLombokDependency extends HR_ORDS_TestBase {

 @Test
    public void testDepartmentPOJO(){

     Department d = new Department();
     d.setDepartment_id(100);
     System.out.println("d.getDepartment_id() = " + d.getDepartment_id());

     Department d2 = new Department(100, "ABC", 12 , 1700);
  System.out.println("d2 = " + d2);

 }

 @DisplayName("Get / Departments and save List of POJO")
 @Test
 public void testDepartmentJsonArrayToListOfPOJO(){

  List<Department> allDeps = get("/departments")
                                .jsonPath().getList("items", Department.class);
  //allDeps.forEach(System.out::println);

  //Copy the content of this List into new list
  // and ONLY print if the Deps Manager id is not NULL
  //allDeps.removeIf(eachDep -> eachDep.getManager_id()!=0 );
  //allDeps.forEach(System.out::println);
  List<Department> allDepsCopy = new ArrayList<>(allDeps);
  allDepsCopy.removeIf(eachDep -> eachDep.getManager_id()==0 );
  allDepsCopy.forEach(System.out::println);


 }

}
