export class Match {
    unique_id: number;
    date: string;
    dateTimeGMT: string;

    team_1: string;
    team_2: string;
    squad: boolean;
    toss_winner_team: string;
    winner_team: string;
    matchStarted: boolean;
    type: string;
    name: string;
    favorited: boolean = false;

    constructor() {

        this.unique_id = 0;
        // this.favorited = false;
        this.winner_team = "";
        this.toss_winner_team = "";


    }
}
