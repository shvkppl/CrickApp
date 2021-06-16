import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticatedGuard } from './authenticated.guard';
import { FavoritesComponent } from './favorites/favorites.component';
import { LoginComponent } from './login/login.component';
import { MatchCalendarComponent } from './match-calendar/match-calendar.component';
import { MatchViewComponent } from './match-view/match-view.component';
import { RecommendationComponent } from './recommendation/recommendation.component';
import { RegisterComponent } from './register/register.component';
import { TrendingComponent } from './trending/trending.component';
import { UpdateuserComponent } from './updateuser/updateuser.component';

const myroute: Routes = [


  {
    path: 'home',
    component: MatchCalendarComponent
  },
  // {
  //   path: 'dashboard',
  //   component: DashboardComponent,
  //   canActivate: [CanActivateRouteGuard],
  //   children: [
  //     {
  //       path: 'view/listview',
  //       component: ListViewComponent
  //     },
  //     {
  //       path: 'view/noteview',
  //       component: NoteViewComponent
  //     },
  //     {
  //       path: '',
  //       redirectTo: 'view/noteview',
  //       pathMatch: 'full'
  //     },
  //     {
  //       path: 'note/:noteId/edit',
  //       component: EditNoteOpenerComponent,
  //       outlet: 'noteEditOutlet'
  //     }
  //   ]
  // },

  // {
  //   path: 'matches',
  //   redirectTo: 'dashboard/view/noteview',
  //   pathMatch: 'full'
  // }

  {
    path: 'matches',
    component: MatchViewComponent
  },

  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'favorites',
    component: FavoritesComponent,
    canActivate: [AuthenticatedGuard]
  },
  {
    path: 'trending',
    component: RecommendationComponent,
    canActivate: [AuthenticatedGuard]
  },
  {
    path: 'updateprofile',
    component: UpdateuserComponent
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];


@NgModule({
  imports: [RouterModule.forRoot(myroute)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
