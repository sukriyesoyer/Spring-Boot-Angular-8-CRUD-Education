import { Employee } from '../education';
import { Component, OnInit, Input } from '@angular/core';
import { EmployeeService } from '../education.service';
import { EmployeeListComponent } from '../education-list/education-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-education-details',
  templateUrl: './education-details.component.html', //Değisti
  styleUrls: ['./education-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  id: number;
  employee: Employee;

  constructor(private route: ActivatedRoute,private router: Router,
    private employeeService: EmployeeService) { }

  ngOnInit() {
    this.employee = new Employee();

    this.id = this.route.snapshot.params['id'];
    
    this.employeeService.getEmployee(this.id)
      .subscribe(data => {
        console.log(data)
        this.employee = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['employees']);
  }
}
