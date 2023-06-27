import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ShopService } from './ShopService';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  constructor(private shopService:ShopService, private router:Router) { }

  shops:any = [];
  ngOnInit(): void {
    this.shopService.getAll().subscribe(s =>{
      this.shops = s;
    });
  }

  onDetail(event:any){
    this.router.navigate([`shop/${event.shopName}`])
  }

}
