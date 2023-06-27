import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ShopDetailComponent } from './shop-detail/shop-detail.component';
import { ShopComponent } from './shop.component';

const routes: Routes = [
  {path:"", component:ShopComponent},
  {path:":name", component:ShopDetailComponent, }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShopRoutingModule { }
