import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TransactionService } from './TransactionService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  constructor( private formBuilder: FormBuilder, private transactionService:TransactionService, private router:Router) { }
  file:any;
  message:String = "";
  ngOnInit(): void {
    
  }

  onChange(event:any){
    try {
      const formData = new FormData();
      formData.append('file', event.target.files[0]);
      this.transactionService.upload(formData).subscribe(r=>{
        this.router.navigate(["/shop"])
      });
    } catch (error) {
      console.error(error);
    }
  }
}
