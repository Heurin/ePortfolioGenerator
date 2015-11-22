package epfg;

/**
 * This class provides setup constants for initializing the application
 * that are NOT language dependent.
 * 
 * @author Seunghyuk Sean Choi
 */
public class StartupConstants {

    // WE'LL LOAD ALL THE UI AND LANGUAGE PROPERTIES FROM FILES,
    // BUT WE'LL NEED THESE VALUES TO START THE PROCESS

    public static String PROPERTY_TYPES_LIST = "property_types.txt";
    public static String UI_PROPERTIES_FILE_NAME = "properties_EN.xml";
    public static String PROPERTIES_SCHEMA_FILE_NAME = "properties_schema.xsd";
    public static String PATH_DATA = "./data/";
    public static String PATH_EPORTFOLIO = PATH_DATA + "eportfolio/";
    public static String PATH_IMAGES = "./images/";
    public static String PATH_ICONS = PATH_IMAGES + "icons/";
    public static String PATH_PAGE_SHOW_IMAGES = PATH_IMAGES + "slide_show_images/";
    public static String PATH_CSS = "/ssm/style/";
    public static String STYLE_SHEET_UI = PATH_CSS + "ePortfolioGeneratorStyle.css";

    // HERE ARE THE LANGUAGE INDEPENDENT GUI ICONS
    public static String ICON_NEW_PAGE = "New.png";
    public static String ICON_LOAD_EPORTFOLIO = "Load.png";
    public static String ICON_SAVE_EPORTFOLIO = "Save.png";
    public static String ICON_VIEW_EPORTFOLIO = "View.png";
    public static String ICON_EXIT = "Exit.png";
    public static String ICON_ADD_PAGE = "Add.png";
    public static String ICON_REMOVE_PAGE = "Remove.png";
    public static String ICON_MOVE_UP = "MoveUp.png";
    public static String ICON_MOVE_DOWN = "MoveDown.png";
    public static String ICON_PREVIOUS = "Previous.png";
    public static String ICON_NEXT = "Next.png";
    public static String ICON_APPLICATION = "application.png";
    public static String ICON_TITLECHANGER ="titlechange.png";
    public static String ICON_PAGESHOW="SlideShow.png";
    
    
    // CSS STYLE SHEET CLASSES
    public static String    CSS_CLASS_VERTICAL_TOOLBAR_BUTTON = "vertical_toolbar_button";
    public static String    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON = "horizontal_toolbar_button";
    public static String    CSS_CLASS_PAGE_EDIT_VBOX = "page_edit_vbox";
    public static String    CSS_CLASS_PAGE_EDIT_VIEW = "page_edit_view";
    public static String    CSS_CLASS_PAGE_SELECTION = "page_selection";
    
    // UI LABELS
    public static String    LABEL_EPORTFOLIO_TITLE = "ePortfolio_title";
}
