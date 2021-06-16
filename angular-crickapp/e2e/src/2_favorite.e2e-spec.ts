// import { Favorite } from './favorites.po';
import { browser, by, element } from 'protractor';
import { Favorite } from './favorites.po';
import { Matches } from './matches.po';

describe('favorites page', () => {
    let page: Favorite;

    let page2: Matches;

    beforeEach(() => {
        page = new Favorite();
        page2 = new Matches();
    });



    it('should not have any favorites', () => {
        page.navigateToFavoritePage();
        expect(element(by.className('mat-card')).isPresent()).toBeFalsy();
    })

    it('should have a favorite added after clicking fav button', () => {
        page2.clickFavButton();
        page.navigateToFavoritePage();
        expect(element(by.className('mat-card')).isPresent()).toBeTruthy();
    })

    it('should not have any card after remove from fav', () => {
        browser.get('/favorites');
        page.clickFav();
        expect(element(by.className('mat-card')).isPresent()).toBeFalsy();

    })

})
