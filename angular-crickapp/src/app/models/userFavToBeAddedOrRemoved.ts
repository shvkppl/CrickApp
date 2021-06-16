export class userFavToBeAddedOrRemoved {
    mailId: string;
    matchId: number;
    constructor(mailid: string, matchid: number) {
        this.mailId = mailid;
        this.matchId = matchid;
    }
}