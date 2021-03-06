package edu.clemson.resolve.jetbrains;

import com.intellij.application.options.colors.PreviewPanel;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/** This object is the controller for the RESOLVE plug-in. It receives
 *  events and can send them on to its contained components; here the main
 *  components being primarily the compiler's console output window.
 */
public class RESOLVEPluginController implements ProjectComponent {

    public static final String PLUGIN_ID = "edu.clemson.resolve.jetbrains";
    public static final Logger LOG = Logger.getInstance("RESOLVEPluginController");

    public static final String CONSOLE_WINDOW_ID = "RESOLVE Output";
    public boolean projectIsClosed = false;

    public Project project;
    public ConsoleView console;
    public ToolWindow consoleWindow;

    public RESOLVEPluginController(@NotNull Project project) {
        this.project = project;
    }

    public static RESOLVEPluginController getInstance(@NotNull Project project) {
        RESOLVEPluginController pc =
                project.getComponent(RESOLVEPluginController.class);
        if ( pc==null ) {
            LOG.error("getInstance: getComponent() for "+
                    project.getName()+" returns null");
        }
        return pc;
    }

    @Override public void initComponent() {
    }

    @Override public void projectOpened() {
        IdeaPluginDescriptor plugin =
                PluginManager.getPlugin(PluginId.getId(PLUGIN_ID));
        String version = "unknown";
        if ( plugin!=null ) {
            version = plugin.getVersion();
        }
        LOG.info("RESOLVE Compiler Plugin version "+version+", Java version "+
                SystemInfo.JAVA_VERSION);
        // make sure the tool windows are created early
        createToolWindows();
        //installListeners();
    }

    public void createToolWindows() {
        LOG.info("createToolWindows "+project.getName());
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        TextConsoleBuilderFactory factory = TextConsoleBuilderFactory.getInstance();
        TextConsoleBuilder consoleBuilder = factory.createBuilder(project);
        this.console = consoleBuilder.getConsole();

        JComponent consoleComponent = console.getComponent();
        Content content = contentFactory.createContent(consoleComponent, "", false);

        consoleWindow = toolWindowManager.registerToolWindow(CONSOLE_WINDOW_ID, true, ToolWindowAnchor.BOTTOM);
        consoleWindow.getContentManager().addContent(content);

        consoleWindow.setIcon(RESOLVEIcons.MODULE);
    }

    @Override public void projectClosed() {
        LOG.info("projectClosed " + project.getName());
        projectIsClosed = true;
        //uninstallListeners();
        
        console.dispose();
        consoleWindow = null;
        project = null;
    }

    public ConsoleView getConsole() {
        return console;
    }

    public ToolWindow getConsoleWindow() {
        return consoleWindow;
    }

    public static void showConsoleWindow(final Project project) {
        ApplicationManager.getApplication().invokeLater(
                new Runnable() {
                    @Override public void run() {
                        RESOLVEPluginController
                                .getInstance(project)
                                .getConsoleWindow()
                                .show(null);
                    }
                }
        );
    }

    @Override public void disposeComponent() {
    }

    @NotNull @Override public String getComponentName() {
        return "resolve.ProjectComponent";
    }
}
