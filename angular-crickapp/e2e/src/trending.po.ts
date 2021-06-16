import { browser, by, element } from 'protractor';

export class TrendingPage {
    // navigate to trending page
    navigateToTrendingPage() {
        return browser.get('/trending');
    }
    // get current URL
    getCurrentURL() {
        return browser.getCurrentUrl();
    }

    // getCardContainer(): ElementFinder
    // {
    //     return element(by.className('fav'));
    // }

    // getAddtoFav(): ElementFinder
    // {
    //     return element(by.className('likefav'));
    // }
    clickFav() {
        element(by.className('favbtn')).click();
    }
}