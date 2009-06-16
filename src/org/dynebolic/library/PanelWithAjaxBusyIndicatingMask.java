package org.dynebolic.library; 

import org.dynebolic.library.WicketAjaxBusyIndicatingMask;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * <b>PanelWithAjaxBusyIndicatingMask</b>
 * 
 * @author valentine.wu
 * @since January 10, 2009
 */

public class PanelWithAjaxBusyIndicatingMask extends Panel implements IAjaxIndicatorAware
{

    private static final long serialVersionUID = 1L;

    // ajax busy indicating mask
    private final WicketAjaxBusyIndicatingMask indicatorAppender;

    /**
     * @param id
     */
    public PanelWithAjaxBusyIndicatingMask(final String id)
    {
        super(id);
        indicatorAppender = new WicketAjaxBusyIndicatingMask();
        add(indicatorAppender);
    }

    /**
     * @param id
     * @param busyText
     */
    public PanelWithAjaxBusyIndicatingMask(final String id, final String busyText)
    {
        super(id);
        indicatorAppender = new WicketAjaxBusyIndicatingMask(busyText);
        add(indicatorAppender);
    }

    /**
     * @param id
     * @param busyText
     * @param indicatorUrl
     */
    public PanelWithAjaxBusyIndicatingMask(final String id, final String busyText, final String indicatorUrl)
    {
        super(id);
        indicatorAppender = new WicketAjaxBusyIndicatingMask(busyText, indicatorUrl);
        add(indicatorAppender);
    }

    /**
     * @param id
     * @param model
     */
    public PanelWithAjaxBusyIndicatingMask(final String id, final IModel model)
    {
        super(id, model);
        indicatorAppender = new WicketAjaxBusyIndicatingMask();
        add(indicatorAppender);
    }

    /**
     * @param id
     * @param model
     * @param busyText
     */
    public PanelWithAjaxBusyIndicatingMask(final String id, final IModel model, final String busyText)
    {
        super(id, model);
        indicatorAppender = new WicketAjaxBusyIndicatingMask(busyText);
        add(indicatorAppender);
    }

    /**
     * @param id
     * @param model
     * @param busyText
     * @param indicatorUrl
     */
    public PanelWithAjaxBusyIndicatingMask(final String id,
                                      final IModel model,
                                      final String busyText,
                                      final String indicatorUrl)
    {
        super(id, model);
        indicatorAppender = new WicketAjaxBusyIndicatingMask(busyText, indicatorUrl);
        add(indicatorAppender);
    }

    /**
     * @see org.apache.wicket.ajax.IAjaxIndicatorAware#getAjaxIndicatorMarkupId()
     */
    public String getAjaxIndicatorMarkupId()
    {
        return indicatorAppender.getMarkupId();
    }

}
