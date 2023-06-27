import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShopRoutingModule } from './shop-routing.module';
import { ShopComponent } from './shop.component';
import { ShopDetailComponent } from './shop-detail/shop-detail.component';


@NgModule({
  declarations: [
    ShopComponent,
    ShopDetailComponent
  ],
  imports: [
    CommonModule,
    ShopRoutingModule
  ]
})
export class ShopModule { }
