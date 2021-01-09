package day09;

import io.restassured.path.json.JsonPath;
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

 @DisplayName("Get / departments and filter the result with JsonPath groovy")
 @Test
 public void testFilterResultWithGroovy(){

  JsonPath jp = get("/departments").jsonPath();
  List<Department> allDeps = jp.getList("items.findAll {it.manager_id > 0 }", Department.class);
  allDeps.forEach(System.out::println);
  //what if I just want to get List<String> to store DepartmentName
  List<String> depNames = jp.getList("items.department_name"); //we get everything
  System.out.println("depNames = " + depNames);

  // -->> items.department_name (all)
  //-->> items.findAll{it.manager_id > 0 }.department_name (filtered for manager_id more than 0)
  List<String> depNamesFilter = jp.getList("items.findAll{it.manager_id > 0 }.department_name");
  System.out.println("depNamesFilter = " + depNamesFilter);

  //Get all departments ID if its more than 70
  List <Integer> allDepsIds = jp.getList("items.department_id");
  System.out.println("allDepsIds = " + allDepsIds);

  List <Integer> allDepsIdsFiltered = jp.getList("items.department_id.findAll{it > 70 }");
  System.out.println("allDepsIdsFiltered = " + allDepsIdsFiltered);


  //what if you have more than one conditions for example: department_id betwwen 70 ~ 100
  List <Integer> deps70to100 = jp.getList("items.department_id.findAll{it >= 70 && it <= 100 }");
  System.out.println("deps70to100 = " + deps70to100);
  //deps70to100 = [70, 80, 90, 100]

  //get the name of the departments if department_id between 70 - 100
  List<String> allDeps70to100 = jp.getList("items.findAll{it.department_id >= 70 && it.department_id <= 100 }.department_name");
  System.out.println("allDeps70to100 = " + allDeps70to100);
  //allDeps70to100 = [Public Relations, Sales, Executive, Finance]

  //findAll -->> will return all matching results
  //find -->> will return first match for the conditions
  String dep10 = jp.getString("items.find{it.department_id == 10}.department_name");
  System.out.println("department 10 name = " + dep10);//Administration

  //sum  / min / max / collect
  //get the sum of the entire department_id
  int sumOfAllDepsIDs = jp.getInt("items.department_id.sum()");
  int sumOfAllDepsIDs1 = jp.getInt("items.sum{it.department_id}");
  System.out.println("sumOfAllDepsIDs = " + sumOfAllDepsIDs);// sumOfAllDepsIDs = 3017
  System.out.println("sumOfAllDepsIDs1 = " + sumOfAllDepsIDs1);//sumOfAllDepsIDs1 = 3017

  //get the lowest department_id
  int lowestDepId = jp.getInt("items.department_id.min()");
  System.out.println("lowestDepId = " + lowestDepId);//lowestDepId = 10

  //get the highest department_id
  int highestDepId = jp.getInt("items.department_id.max()");
  System.out.println("highestDepId = " + highestDepId);//highestDepId = 240

  //print number 5 dep_Id
  System.out.println(jp.getInt("items.department_id[4]"));//40

  //print number last dep_Id
  System.out.println(jp.getInt("items.department_id[-1]"));//240

  //print from index 7 till index 10 dep_Id
  System.out.println("index 7-10 dep_Id " + jp.getList("items.department_id[7..10]"));//[70, 80, 90, 100]


 }


}
