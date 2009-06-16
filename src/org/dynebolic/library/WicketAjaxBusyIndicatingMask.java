package org.dynebolic.library;

import org.apache.wicket.Component;
import org.apache.wicket.Response;
import org.apache.wicket.extensions.ajax.markup.html.WicketAjaxIndicatorAppender;

/**
 * <b>WicketAjaxBusyIndicatingMask</b>
 * 
 * @author valentine.wu
 * @since January 10, 2009
 */

public class WicketAjaxBusyIndicatingMask extends WicketAjaxIndicatorAppender
{
    private static final long serialVersionUID = 1L;

    private final String busyIndicatorText;
    private final String busyIndicatorUrl;
    private final static String DEFAULT_BUSY_INDICATOR_TEXT = "Please wait...";
    
    public WicketAjaxBusyIndicatingMask()
    {
        this(DEFAULT_BUSY_INDICATOR_TEXT);
    }
    
    public WicketAjaxBusyIndicatingMask(final String busyText) 
    {
        this(busyText, null);
    }
    
    public WicketAjaxBusyIndicatingMask(final String busyText, final String indicatorUrl) 
    {
        busyIndicatorText = busyText;
        busyIndicatorUrl = indicatorUrl;
    }
    
    /**
     * @see org.apache.wicket.behavior.AbstractBehavior#onRendered(org.apache.wicket.Component)
     */
    public void onRendered(Component component)
    {
        final Response r = component.getResponse();

        r.write("<span style=\"display:none;");
        r.write("position:absolute; left:1px; top:1px; margin:0px 0px 0px 0px; z-index:9999; width:100%; clear:none; ");
        r.write("height:expression(document.body.scrollHeight>document.body.offsetHeight ? document.body.scrollHeight: document.body.offsetHeight + 'px'); \" class=\"");
        r.write(getSpanClass());
        r.write("\" ");
        r.write("id=\"");
        r.write(getMarkupId());
        r.write("\"><table align=\"center\" width=\"100%\" height=\"100%\"><tr><td align=\"center\">");
        r.write("<span style=\"background-color:transparent;\"><img src=\"");
        if (busyIndicatorUrl == null)
        {
            r.write(getIndicatorUrl());
        }
        else
        {
            r.write(busyIndicatorUrl);
        }
        r.write("\" alt=\"\"/>");
        r.write(busyIndicatorText);
        r.write("</span></td></tr></table></span>");
    }

}