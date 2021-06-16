import { Component, Input } from '@angular/core';
import { UpcomingMatch } from '../models/upcomingMatch';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-upcomingMatch',
  templateUrl: './upcomingMatch.component.html',
  styleUrls: ['./upcomingMatch.component.css']
})
export class UpcomingMatchComponent {

  @Input()
  upcomingMatch: UpcomingMatch;
  constructor(private route: RouterService) { }

  getScoreView() {
    // this.route.routeToEditNoteView(this.note.id);
  }
}
