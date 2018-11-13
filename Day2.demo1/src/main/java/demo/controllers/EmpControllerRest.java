package demo.controllers;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.model.Emp;

@RestController
public class EmpControllerRest {

	List<Emp> list = new ArrayList<Emp>();
	
	public EmpControllerRest(){
		for(int i =1; i<60;i++){
			Emp e = new Emp(i, "Name"+i, i+10000);
			list.add(e);
		}
	}
	@RequestMapping(method = RequestMethod.GET)
	public List<Emp> retrive(){
		return list;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{empno}")
	public ResponseEntity<Emp> retrive(@PathVariable(name = "empno") int empno){
		Optional<Emp> oemp = list.stream().filter((e1)-> e1.getEmpno() == empno).findFirst();
		if(oemp.isPresent()){
			return ResponseEntity.status(200).body(oemp.get());
		}else
			return ResponseEntity.status(400).build();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getEmployee")
	public List<Emp> retrive(@RequestParam(name="page")int page,@RequestParam(name="pagesize")int pagesize){
		return list.stream().skip(page*pagesize).limit(pagesize).collect(Collectors.toList());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveEmployee")
	public void create(@RequestBody Emp emp) throws Exception{
		if(list.stream().anyMatch(e1 -> e1.getEmpno()== emp.getEmpno())){
			throw new Exception("Duplicate emp number");
		}else {
			list.add(emp);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteEmployee/{empno}")
	public void delete(@PathVariable("empno") int empno) throws Exception{
		if(list.removeIf(e1 -> e1.getEmpno()== empno))
		throw new Exception("Employee emp number does not exist");
		
	}
	
	@RequestMapping(method = RequestMethod.PATCH, value = "/updateEmployee")
	public void updatePatch(@RequestBody Emp emp){
		if(list.stream().anyMatch(e1 -> e1.getEmpno()== emp.getEmpno())){
			list.replaceAll(emp1 -> {
			//emp1.setEmpno(emp.getEmpno());
		   if(emp.getEname() != null)
			   emp1.setEname(emp.getEname());
		   if(emp.getSalary() != 0)
			   emp1.setSalary(emp.getSalary());
			return emp1;
			});
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateEmployee")
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
