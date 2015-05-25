package gui.forms.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

/**
 * Denna TextPrompt-klass kommer att visa en prompt ovanpå en textdelen när
 * textfältet är tomt. Show-egenskaperna används för att
 * bestämma synlighet prompten.
 */
@SuppressWarnings("serial")
public class TextPrompt extends JLabel
	implements FocusListener, DocumentListener
{
	public enum Show
	{
		ALWAYS,
		FOCUS_GAINED,
		FOCUS_LOST;
	}

	private JTextComponent component;
	private Document document;

	private Show show;
	private boolean showPromptOnce;
	private int focusLost;

	public TextPrompt(String text, JTextComponent component)
	{
		this(text, component, Show.ALWAYS);
	}

	public TextPrompt(String text, JTextComponent component, Show show)
	{
		this.component = component;
		setShow( show );
		document = component.getDocument();

		setText( text );
		setFont( component.getFont() );
		setForeground( component.getForeground() );
		setBorder( new EmptyBorder(component.getInsets()) );
		setHorizontalAlignment(JLabel.LEADING);

		component.addFocusListener( this );
		document.addDocumentListener( this );

		component.setLayout( new BorderLayout() );
		component.add( this );
		checkForPrompt();
	}

	/**
	 * Metod för att ändra alfavärdet för den aktuella förgrunden.
	 * Färg till specificed värde.
	 * @param alpha alfavärde i intervallet 0 - 1.0.
	 */
	public void changeAlpha(float alpha)
	{
		changeAlpha( (int)(alpha * 255) );
	}

	/**
	 * Metod för att ändra alfavärdet för den aktuella förgrunden.
	 * Färg till specificed värde.
	 * @param alpha alfavärde i intervallet 0 - 255.
	 */
	public void changeAlpha(int alpha)
	{
		alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;

		Color foreground = getForeground();
		int red = foreground.getRed();
		int green = foreground.getGreen();
		int blue = foreground.getBlue();

		Color withAlpha = new Color(red, green, blue, alpha);
		super.setForeground( withAlpha );
	}

	/**
	 * Metod för att ändra stilen på det aktuella teckensnittet. Formatmallens
	 * värden finns i klassen Font. Gemensamma värden kan vara:
	 * Font.BOLD, Font.ITALIC och Font.BOLD + Font.ITALIC.
	 * @param style fontstilsvärde som representerar den nya stilen i Font.
	 */
	public void changeStyle(int style)
	{
		setFont( getFont().deriveFont( style ) );
	}

	/**
	 *  Hämta Show-egenskapen
	 *  @return Show-egenskapen.
	 */
	public Show getShow()
	{
		return show;
	}

	/**
	 * Ställ promptens Show-egenskapen till att kontrollera när promten ska visas.
	 * Giltiga värden är:
	 *
	 * Show.ALWAYS (standard) - visar alltid prompten
	 * Show.Focus_GAINED - visa prompten när komponentfokus ges
	 * (och dölja prompten när fokus försvinner)
	 * Show.Focus_LOST - visa prompten när komponenten förlorar fokus
	 * (och dölja prompt när fokus har getts)
	 *
	 * @param show Show-enum
	 */
	public void setShow(Show show)
	{
		this.show = show;
	}

	/**
	 *  Hämta egenskapen om showPromptOnce
	 * @return showPromptOnce-egenskapen.
	 */
	public boolean getShowPromptOnce()
	{
		return showPromptOnce;
	}

	/**
	 *  Visa prompten gång. När komponenten har fått / tappat fokus
	 * en gång, kommer prompt inte att visas igen.
	 * @param showPromptOnce 	when true visas prompten endast en gång,
	 * 							annars kommer det att visas upprepade gånger.
	 */
	public void setShowPromptOnce(boolean showPromptOnce)
	{
		this.showPromptOnce = showPromptOnce;
	}

	/**
	 * Kontrollera om prompten ska vara synlig eller inte. Synligheten kommer 
	 * att förändras på uppdateringar av dokument och på fokusändringar.
	 */
	private void checkForPrompt()
	{
		//  Text har matats in, ta bort prompten

		if (document.getLength() > 0)
		{
			setVisible( false );
			return;
		}

		//  Prompt har redan visats en gång, ta bort det

		if (showPromptOnce && focusLost > 0)
		{
			setVisible(false);
			return;
		}

		//  Kontrollerar Show-egenskapen och komponentfokus för att avgöra om
		// prompt ska visas.

        if (component.hasFocus())
        {
        	if (show == Show.ALWAYS
        	||  show ==	Show.FOCUS_GAINED)
        		setVisible( true );
        	else
        		setVisible( false );
        }
        else
        {
        	if (show == Show.ALWAYS
        	||  show ==	Show.FOCUS_LOST)
        		setVisible( true );
        	else
        		setVisible( false );
        }
	}

//  Implementerar FocusListener

	public void focusGained(FocusEvent e)
	{
		checkForPrompt();
	}

	public void focusLost(FocusEvent e)
	{
		focusLost++;
		checkForPrompt();
	}

//  Implementerar DocumentListener

	public void insertUpdate(DocumentEvent e)
	{
		checkForPrompt();
	}

	public void removeUpdate(DocumentEvent e)
	{
		checkForPrompt();
	}

	public void changedUpdate(DocumentEvent e) {}
}