import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShopService } from '../ShopService';

@Component({
  selector: 'app-shop-detail',
  templateUrl: './shop-detail.component.html',
  styleUrls: ['./shop-detail.component.css']
})
export class ShopDetailComponent implements OnInit {

  constructor(private shopService:ShopService, private activeRouter:ActivatedRoute) { }

  transactions:any = [];
  amount:any;
  shopName:String = "";
  ngOnInit(): void {
    this.shopName = this.activeRouter.snapshot.params["name"];
    this.shopService.getInfo(this.shopName).subscribe(info=>{
      this.transactions = info.transactions;
      this.amount = info.amountTotal;
    });
  }

}
