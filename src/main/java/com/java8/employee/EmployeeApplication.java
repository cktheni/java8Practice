package com.java8.employee;

import com.java8.employee.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.html.Option;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmployeeApplication.class, args);

		System.out.println("Java 8 printing 1 to 100 ");
		System.out.println("======================== ");
		//Print 1 to 100 using Java 8
		IntStream.range(1, 101).forEach(n -> { System.out.println(n); });

		System.out.println("*************************************************************** ");

		//Top 5 Joining data of employee
		List<Employee> employeeList = new ArrayList();

		Employee e1 = new Employee("101","Saravanan",LocalDateTime.of(2012,8,25,10,05),false,10);		//1
		Employee e2 = new Employee("102","Chandru",LocalDateTime.of(2016,9,29,10,15),false,15);		//3
		Employee e3 = new Employee("103","Selva",LocalDateTime.of(2015,3,2,9,20),true,10);			//2
		Employee e4 = new Employee("104","Mani",LocalDateTime.of(2019,02,10,11,10),false,12);			//7
		Employee e5 = new Employee("105","Ranjith",LocalDateTime.of(2018,06,03,12,20),false,6);		//6
		Employee e6 = new Employee("106","Arun",LocalDateTime.of(2017,6,2,8,15),false,8);				//5
		Employee e7 = new Employee("107","Prasana",LocalDateTime.of(2019,12,03,9,20),false,5);		//8
		Employee e8 = new Employee("108","Guru",LocalDateTime.of(2016,9,25,8,20),true,17);			//4

		LocalDateTime.of(2015,02,12,01,20);

		employeeList.add(e1);	employeeList.add(e2);employeeList.add(e3);employeeList.add(e4);employeeList.add(e5);employeeList.add(e6);employeeList.add(e7);employeeList.add(e8);

		System.out.println("Java 8 Iterate List of Employees");
		System.out.println("======================== ");

		employeeList.stream().forEach(s -> System.out.println(s.getEmployeeName() +"-"+s.getJoiningDate()));

		System.out.println("*************************************************************** ");

		System.out.println("Java 8 List employee based on Joining date sorting and Limit with 5 (Top 5 Joining Dates)");
		System.out.println("========================================================================================= ");

		//employeeList.stream().sorted((emp1,emp2)->emp1.getJoinin(gDate().compareTo(emp2.getJoiningDate()));

		employeeList.sort(Comparator.comparing(Employee::getJoiningDate));

		employeeList.stream().limit(5).forEach(s -> System.out.println(s.getEmployeeName() +"-"+s.getJoiningDate()));

		System.out.println("*************************************************************** ");

		System.out.println("Java 8 List employee names  joined from 2016 onwards");
		System.out.println("=================================================== ");


		//Employees joined from 2016 onwards
		employeeList.stream()
				.filter(e -> e.getJoiningDate().getYear() >= 2016)
				.map(Employee::getEmployeeName)
				.forEach(System.out::println);

		System.out.println("*************************************************************** ");

		LocalDateTime joinDtTimeInput = LocalDateTime.parse("2016-09-29T10:15");

		System.out.println("After joinDtTimeInput:"+joinDtTimeInput);
		System.out.println("=================================== ");


		employeeList.stream()
				.filter(e -> e.getJoiningDate().isAfter(joinDtTimeInput))
				.map(Employee::getEmployeeName)
				.forEach(System.out::println);

		System.out.println("*************************************************************** ");

		System.out.println("Java 8 Current Date and Time");

		LocalDateTime  today =LocalDateTime.now(ZoneId.systemDefault());

		System.out.println("today ="+today);


		System.out.println("*************************************************************** ");


		System.out.println("Java 8  Date after 1 week from today");

		LocalDateTime  afterWeek = today.plus(1,ChronoUnit.WEEKS);

		System.out.println("After 1 Week, The date is  ="+afterWeek);

		System.out.println("*************************************************************** ");

		DayOfWeek dayOfWeek
				= DayOfWeek.from(today);

		System.out.println("dayOfWeek  ="+dayOfWeek.name());

		LocalDate startDate = today.toLocalDate();
		LocalDate endDate = afterWeek.toLocalDate();

		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);

		IntStream.iterate(0, i -> i + 1)
				.limit(numOfDaysBetween)
				.mapToObj(i -> startDate.plusDays(i))
				.collect(Collectors.toList()).forEach(System.out::println);

		System.out.println("*************************************************************** ");


		LocalDate starWeek = startDate.with(DayOfWeek.MONDAY);

		LocalDate startNextWeek =  starWeek.plus(1, ChronoUnit.WEEKS);

		System.out.println("Start Date of Next week:"+startNextWeek);

		LocalDate endNextWeek= startNextWeek.plus(1, ChronoUnit.WEEKS);

		System.out.println("End  Date of Next week:"+endNextWeek);

		System.out.println("*************************************************************** ");


		System.out.println("****Next Week  All Dates**** ");
		System.out.println("=================================== ");

		long numOfDaysBetweenNextWeek = ChronoUnit.DAYS.between(startNextWeek, endNextWeek);

		IntStream.iterate(0, i -> i + 1)
				.limit(numOfDaysBetweenNextWeek)
				.mapToObj(i -> startNextWeek.plusDays(i))
				.collect(Collectors.toList()).forEach(System.out::println);

		System.out.println("*************************************************************** ");


		System.out.println("All Working days of next week");
		System.out.println("=================================== ");

		IntStream.iterate(0, i -> i + 1)
				.limit(numOfDaysBetweenNextWeek)
				.mapToObj(i -> (startNextWeek.plusDays(i))).filter(obj->isWeekDay(obj))
				.collect(Collectors.toList()).forEach(System.out::println);

		System.out.println("*************************************************************** ");

		System.out.println("List of Manager Employees");
		System.out.println("=================================== ");

		employeeList.stream()
				.filter(e -> e.isManager())
				.map(Employee::getEmployeeName)
				.forEach(System.out::println);

		System.out.println("List of Regular  Employees");
		System.out.println("=================================== ");

		employeeList.stream()
				.filter(e -> !e.isManager())
				.map(Employee::getEmployeeName)
				.forEach(System.out::println);

		System.out.println("*************************************************************** ");


		List<Employee> dnaCanidateEmpList = employeeList.stream().filter(e->e.getExperience()>=7).collect(Collectors.toList());
		System.out.println("Count of dnaCanidateEmpList (exp >7)"+ Optional.ofNullable(dnaCanidateEmpList.size()) +"The List");
		System.out.println("=================================================================================== ");


		dnaCanidateEmpList.stream().forEach(s -> System.out.println(s.getEmployeeName() +"-"+s.getExperience()));

		System.out.println("*************************************************************** ");

	}

	public static boolean isWeekDay(LocalDate localDte) {
		if( !(localDte.getDayOfWeek() == DayOfWeek.SATURDAY || localDte.getDayOfWeek() ==DayOfWeek.SUNDAY))
			return true;
		else
			return false;
	}
}
