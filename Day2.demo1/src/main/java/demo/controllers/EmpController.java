package demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.model.Emp;

@RestController
public class EmpController {

	List<Emp> list = new ArrayList<Emp>();
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveEmployee1")
	public void create(@RequestBody Emp emp) throws Exception{
		if(list.stream().anyMatch(e1 -> e1.getEmpno()== emp.getEmpno())){
			throw new Exception("Duplicate emp number");
		}else {
			list.add(emp);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteEmployee1/{empno}")
	public void delete(@PathVariable("empno") int empno) throws Exception{
		if(list.removeIf(e1 -> e1.getEmpno()== empno))
		throw new Exception("Employee emp number does not exist");
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getEmployee1")
	public List<Emp> retrive(){
		return list;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateEmployee1")
	public void update(@RequestBody Emp emp){
		if(list.stream().anyMatch(e1 -> e1.getEmpno()== emp.getEmpno())){
			list.replaceAll(emp1 -> {
			emp1.setEmpno(emp.getEmpno());
			emp1.setEname(emp.getEname());
			emp1.setSalary(emp.getSalary());
			return emp1;
			});
		}
	}
}
