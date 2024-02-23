import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HomeComponent } from './app/components/home/home.component';
import { HeaderComponent } from './app/components/header/header.component';
import { FooterComponent } from './app/components/footer/footer.component';
import { ProductComponent } from './app/components/product/product.component';
import { DetailProductComponent } from './app/components/detail-product/detail-product.component';
import { OrderComponent } from './app/components/order/order.component';
import { LoginComponent } from './app/components/login/login.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

//fix loi khong tao duoc http client
import { 
  HttpClientModule, 
  HTTP_INTERCEPTORS 
} from '@angular/common/http';
import {TokenInterceptor} from './app/interceptors/token.interceptor';
import { AppComponent } from './app/app/app.component'
import { AppRoutingModule } from './app-routing.module';
import { CartComponent } from './app/components/cart/cart.component';
import { LoveComponent } from './app/components/love/love.component';
import { OrderDetailComponent } from './app/components/detail-order/order.detail.component';
import { FindOrderComponent } from './app/components/find-order/find-order.component';
import { RegisterComponent } from './app/components/register/register.component';
import { UserProfileComponent } from './app/components/user-profile/user.profile.component';
import { AdminComponent } from './app/components/admin/admin.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AdminModule } from './app/components/admin/admin.module';





@NgModule({
  declarations: [
    
    HomeComponent,
         HeaderComponent,
         FooterComponent,
         ProductComponent,
         DetailProductComponent,
         OrderComponent,
        OrderDetailComponent,
         LoginComponent,
         RegisterComponent,
         AppComponent,
         CartComponent,
         LoveComponent,
         FindOrderComponent,
         UserProfileComponent,
         AppComponent,
       
        
  ],
  imports: [
    ReactiveFormsModule,
    [HttpClientModule,  ],
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,        
    AdminModule,
  ],
  providers: [  {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true,
  },],
  bootstrap: [AppComponent]
    
})
export class AppModule { }
