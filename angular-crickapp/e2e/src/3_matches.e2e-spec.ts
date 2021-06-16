// import { Favorite } from './favorites.po';
import { browser, by, element } from 'protractor';
import { Favorite } from './favorites.po';
import { Matches } from './matches.po';
import { TrendingPage } from "./trending.po";

describe('matches page', () => {
    let page: Favorite;
    let page2: Matches;

    beforeEach(() => {
        page = new Favorite();
        page2 = new Matches();
    });

    it('should have matches', () => {
        page2.navigateToMatches();
        expect(element(by.className('mat-card')).isPresent()).toBeTruthy();
    })


});


// describe('trending page', () => {

// });