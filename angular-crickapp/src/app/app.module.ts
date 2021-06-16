import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UpcomingMatchComponent } from './upcomingMatch/upcomingMatch.component';
import { MatchViewComponent } from './match-view/match-view.component';
import { MatIconModule } from '@angular/material/icon';
import { ToastrModule } from 'ngx-toastr';


import { Routes, RouterModule } from '@angular/router';
// import { HeaderComponent } from './header/header.component';
// import { LoginComponent } from './login/login.component';
// import { DashboardComponent } from './dashboard/dashboard.component';
// import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// import { MatExpansionModule } from '@angular/material/expansion';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
// import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
// import {MatLabelModule} from '@angular/material/label';
import { MatButtonModule } from '@angular/material/button';
// import { CanActivateRouteGuard } from './can-activate-route.guard';
// import { AuthenticationService } from './services/authentication.service';

import { MatDialogModule } from '@angular/material/dialog';
// import { MatchTakerComponent } from './Match-taker/Match-taker.component';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatchesService } from './services/matches.service';
import { HttpClientModule } from '@angular/common/http';
import { MatchComponent } from './match/match.component';
import { MatchCalendarComponent } from './match-calendar/match-calendar.component';

import { NgxPaginationModule } from 'ngx-pagination';
import { MatTableModule } from '@angular/material/table';
import { HeaderComponent } from './header/header.component';
import { FavoritesComponent } from './favorites/favorites.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { TrendingComponent } from './trending/trending.component';
import { UserService } from './services/user.service';
import { AuthenticationService } from './services/authentication.service';
import { RouterService } from './services/router.service';
import { FlexLayoutModule } from '@angular/flex-layout';
import { LayoutModule } from '@angular/cdk/layout';
import { MatListModule } from '@angular/material/list';
import { RecommendationComponent } from './recommendation/recommendation.component';
import { TrendingMatchComponent } from './trending-match/trending-match.component';
import { MatSnackBarModule, MAT_SNACK_BAR_DEFAULT_OPTIONS } from '@angular/material/snack-bar';
import { UpdateuserComponent } from './updateuser/updateuser.component'



@NgModule({
  declarations: [
    AppComponent,
    UpcomingMatchComponent,
    MatchViewComponent,
    MatchComponent,
    MatchCalendarComponent,
    HeaderComponent,
    FavoritesComponent,
    LoginComponent,
    RegisterComponent,
    TrendingComponent,
    RecommendationComponent,
    TrendingMatchComponent,
    UpdateuserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    HttpClientModule,
    MatIconModule,
    MatToolbarModule,
    MatButtonModule,
    NgxPaginationModule,
    MatTableModule,
    MatSidenavModule,
    FlexLayoutModule,
    ToastrModule.forRoot(),
    LayoutModule,
    MatListModule,
    MatSnackBarModule
  ],
  providers: [MatchesService, UserService, AuthenticationService, RouterService,
    { provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: { duration: 1000 } }],
  bootstrap: [AppComponent]
})
export class AppModule { }
