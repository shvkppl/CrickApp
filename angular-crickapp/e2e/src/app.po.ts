import { browser, by, element, ElementFinder } from 'protractor';

export class AppPage {
  async navigateTo(): Promise<unknown> {
    return browser.get(browser.baseUrl);
  }

  getMatFormField(): ElementFinder {

    return element(by.className('full-width-input'));
  }

  getHeaderText(): ElementFinder {

    return element(by.className('cls1'));
  }
}
