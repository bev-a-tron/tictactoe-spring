package com.springapp.mvc;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(JUnitReportingRunner.class)
public class TicTacToeJBehave extends JUnitStories {

    public TicTacToeJBehave() {
        super();
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(this.configuration(), new TicTacToeSteps());
    }

    @Override
    public List<String> storyPaths() {
        return Arrays.asList("tictactoe/TicTacToe.story");
    }

}


//import org.jbehave.web.examples.trader.webdriver.pages.Pages;
//import org.jbehave.web.selenium.*;

//public class TraderWebStories extends JUnitStories {
//
//    private WebDriverProvider driverProvider = new PropertyWebDriverProvider();
//    private WebDriverSteps lifecycleSteps = new PerStoriesWebDriverSteps(driverProvider); // or PerStoryWebDriverSteps(driverProvider)
//    private Pages pages = new Pages(driverProvider);
//    private SeleniumContext context = new SeleniumContext();
//    private ContextView contextView = new LocalFrameContextView().sized(500, 100);
//
//    public TraderWebStories() {
//        // If configuring lifecycle per-stories, you need to ensure that you a same-thread executor
//        if ( lifecycleSteps instanceof PerStoriesWebDriverSteps ){
//            configuredEmbedder().useExecutorService(MoreExecutors.sameThreadExecutor());
//        }
//    }
//
//    @Override
//    public Configuration configuration() {
//        Class<? extends Embeddable> embeddableClass = this.getClass();
//        return new SeleniumConfiguration()
//                .useSeleniumContext(context)
//                .useWebDriverProvider(driverProvider)
//                .useStepMonitor(new SeleniumStepMonitor(contextView, context, new SilentStepMonitor()))
//                .useStoryLoader(new LoadFromClasspath(embeddableClass))
//                .useStoryReporterBuilder(new StoryReporterBuilder()
//                        .withCodeLocation(codeLocationFromClass(embeddableClass))
//                        .withDefaultFormats()
//                        .withFormats(CONSOLE, TXT, HTML, XML));
//    }
//
//    @Override
//    public InjectableStepsFactory stepsFactory() {
//        Configuration configuration = configuration();
//        return new InstanceStepsFactory(configuration,
//                new TraderWebSteps(pages),
//                lifecycleSteps,
//                new WebDriverScreenshotOnFailure(driverProvider, configuration.storyReporterBuilder()));
//    }
//
//
//    @Override
//    protected List<String> storyPaths() {
//        return new StoryFinder()
//                .findPaths(codeLocationFromClass(this.getClass()).getFile(), asList("**/*.story"), null);
//    }
//
//    // This Embedder is used by Maven or Ant and it will override anything set in the constructor
//    public static class SameThreadEmbedder extends Embedder {
//
//        public SameThreadEmbedder() {
//            useExecutorService(MoreExecutors.sameThreadExecutor());
//        }
//
//    }
//
//}