package xxl.app.main;

import xxl.Calculator;

/**
 * Menu builder for managers.
 */
public class Menu extends pt.tecnico.uilib.menus.Menu {

    public Menu(Calculator receiver) {
        super(Label.TITLE, //
                new DoNew(receiver), //
                new DoOpen(receiver), //
                new DoSave(receiver), //
                new DoMenuOpenEdit(receiver), //
                new DoMenuOpenSearch(receiver) //
        );
    }

}