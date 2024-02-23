import { Router, RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./app/components/home/home.component";
import { LoginComponent } from "./app/components/login/login.component";

import { DetailProductComponent } from "./app/components/detail-product/detail-product.component";
import { OrderComponent } from "./app/components/order/order.component";
import { NgModule } from "@angular/core";
import { ProductComponent } from "./app/components/product/product.component";
import { CartComponent } from "./app/components/cart/cart.component";
import { LoveComponent } from "./app/components/love/love.component";
import { OrderDetailComponent } from "./app/components/detail-order/order.detail.component";
import { FindOrderComponent } from "./app/components/find-order/find-order.component";
import { RegisterComponent } from "./app/components/register/register.component";
import { AdminComponent } from "./app/components/admin/admin.component";
import { UserProfileComponent } from "./app/components/user-profile/user.profile.component";


const routes:Routes=[
    {path:'', component:HomeComponent},
    {path:'login', component:LoginComponent},
    {path:'register', component:RegisterComponent},
    {path:'products', component:ProductComponent},
    {path:'products/:id', component:DetailProductComponent},
    {path:'order', component:OrderComponent},
    {path:'ordert', component:OrderComponent},
    {path:'order/:id', component:OrderDetailComponent},
    {path:'find', component:FindOrderComponent},
    {path:'love', component:LoveComponent},
    {path:'cart', component:CartComponent},
    {path:'user-profile', component:UserProfileComponent},
    {path:'admin', component:AdminComponent},
];

@NgModule({
    imports:[RouterModule.forRoot(routes)],
    exports:[RouterModule]
})
export class AppRoutingModule{}