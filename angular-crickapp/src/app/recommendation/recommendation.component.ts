import { Component, OnInit } from '@angular/core';
import { Match } from '../models/match';
import { MatchCounter } from '../models/matchCounter';
import { RecserveService } from '../recserve.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent implements OnInit {

  matchobj: MatchCounter;
  matchdata: Array<MatchCounter> = [];
  msg: string;
  match: Match;
  q: any;

  constructor(private recserve: RecserveService, private route: RouterService) {
    this.matchobj = new MatchCounter();
  }

  ngOnInit(): void {
    // if(sessionS)
    this.getMatches();
  }


  getMatches() {
    console.log("inside matches");
    this.recserve.getTrendingMatches().subscribe(
      matches => {
        this.matchdata = matches;
        console.log("printing sub data");
        console.log(this.matchdata);

      },
      err => {
        console.log(err);
        this.msg = err;
        sessionStorage.clear();
        this.route.routeToLogin();
      }
    );
    console.log("printing all the matches details")
    console.log(this.matchdata);
  }




  displayMatch() {
    // this.recserve.getallMatches().subscribe(
    //   (response)=>
    //   {
    //     this.matchdata=response;
    //     // console.log(response);
    //     console.log(this.matchdata);
    //   },
    //   (error )=> {
    //     this.msg = ' Not Found';
    //   });






  }

}
