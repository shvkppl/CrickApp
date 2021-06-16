import { browser, by, element } from "protractor";
import { Matches } from "./matches.po";
import { TrendingPage } from "./trending.po";

describe('trending page', () => {
    let page: TrendingPage;
    let page1: Matches;
    let page2: TrendingPage;

    beforeEach(() => {
        page = new TrendingPage();
        page1 = new Matches();
        page2 = new TrendingPage();

    });

    it('Checking trending page  is empty:Should not have any Card initially', () => {
        page.navigateToTrendingPage();
        expect(element(by.className('mat-card')).isPresent()).toBeFalsy();
    })

    it('Should have Matches', () => {
        page1.navigateToMatches();
        expect(element(by.className('mat-card')).isPresent()).toBeTruthy();
    })

    it('Should have a favourite added after clicking fav button', () => {
        page1.clickFavButton();
        page2.navigateToTrendingPage();
        expect(element(by.className('mat-card')).isPresent()).toBeTruthy();
    })
    it('Should not have any card after removing Matches from favorite', () => {
        browser.get('/trending')
        page2.clickFav();
        expect(element(by.className('mat-card')).isPresent()).toBeFalsy();
    })

});