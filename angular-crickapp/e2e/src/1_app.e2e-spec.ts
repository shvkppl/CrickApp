import { browser, logging } from 'protractor';
import { AppPage } from './app.po';


describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  // it('should display welcome message', async () => {
  //   await page.navigateTo();
  //   expect(await page.getTitleText()).toEqual('cricAppSPA app is running!');
  // });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});

describe('crickapp e2e tests', () => {
  it('should display login', () => {
    browser.get('./login');
    expect(browser.getCurrentUrl()).toContain('login');
    browser.sleep(5000);
  })
})


describe('crickapp e2e tests', () => {
  it('should display register', () => {
    browser.get('./register');
    expect(browser.getCurrentUrl()).toContain('register');
    browser.sleep(5000);
  })
})

describe('crickapp e2e tests', () => {
  it('should display home', () => {
    browser.get('./home');
    expect(browser.getCurrentUrl()).toContain('home');
    browser.sleep(5000);
  })
})