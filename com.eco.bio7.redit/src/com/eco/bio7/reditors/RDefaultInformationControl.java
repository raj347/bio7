package com.eco.bio7.reditors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.internal.text.html.HTMLTextPresenter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.AbstractInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.util.Geometry;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import com.eco.bio7.browser.BrowserView;
import com.eco.bio7.rbridge.RState;

/**
 * Default implementation of {@link org.eclipse.jface.text.IInformationControl}.
 * <p>
 * Displays textual information in a {@link org.eclipse.swt.custom.StyledText}
 * widget. Before displaying, the information set to this information control is
 * processed by an <code>IInformationPresenter</code>.
 *
 * @since 2.0
 */
public class RDefaultInformationControl extends AbstractInformationControl implements DisposeListener {

	/**
	 * An information presenter determines the style presentation of information
	 * displayed in the default information control. The interface can be
	 * implemented by clients.
	 */
	public interface IInformationPresenter {

		/**
		 * Updates the given presentation of the given information and thereby
		 * may manipulate the information to be displayed. The manipulation
		 * could be the extraction of textual encoded style information etc.
		 * Returns the manipulated information.
		 * <p>
		 * <strong>Note:</strong> The given display must only be used for
		 * measuring.
		 * </p>
		 *
		 * @param display
		 *            the display of the information control
		 * @param hoverInfo
		 *            the information to be presented
		 * @param presentation
		 *            the presentation to be updated
		 * @param maxWidth
		 *            the maximal width in pixels
		 * @param maxHeight
		 *            the maximal height in pixels
		 *
		 * @return the manipulated information
		 * @deprecated As of 3.2, replaced by
		 *             {@link RDefaultInformationControl.IInformationPresenterExtension#updatePresentation(Drawable, String, TextPresentation, int, int)}
		 */
		String updatePresentation(Display display, String hoverInfo, TextPresentation presentation, int maxWidth, int maxHeight);
	}

	/**
	 * An information presenter determines the style presentation of information
	 * displayed in the default information control. The interface can be
	 * implemented by clients.
	 *
	 * @since 3.2
	 */
	public interface IInformationPresenterExtension {

		/**
		 * Updates the given presentation of the given information and thereby
		 * may manipulate the information to be displayed. The manipulation
		 * could be the extraction of textual encoded style information etc.
		 * Returns the manipulated information.
		 * <p>
		 * Replaces
		 * {@link RDefaultInformationControl.IInformationPresenter#updatePresentation(Display, String, TextPresentation, int, int)}
		 * Implementations should use the font of the given
		 * <code>drawable</code> to calculate the size of the text to be
		 * presented.
		 * </p>
		 *
		 * @param drawable
		 *            the drawable of the information control
		 * @param hoverInfo
		 *            the information to be presented
		 * @param presentation
		 *            the presentation to be updated
		 * @param maxWidth
		 *            the maximal width in pixels
		 * @param maxHeight
		 *            the maximal height in pixels
		 *
		 * @return the manipulated information
		 */
		String updatePresentation(Drawable drawable, String hoverInfo, TextPresentation presentation, int maxWidth, int maxHeight);
	}

	/**
	 * Inner border thickness in pixels.
	 * 
	 * @since 3.1
	 */
	private static final int INNER_BORDER = 1;

	/** The control's text widget */
	private StyledText fText;
	/** The information presenter, or <code>null</code> if none. */
	private final IInformationPresenter fPresenter;
	/** A cached text presentation */
	private final TextPresentation fPresentation = new TextPresentation();

	/**
	 * Additional styles to use for the text control.
	 * 
	 * @since 3.4, previously called <code>fTextStyle</code>
	 */
	private final int fAdditionalTextStyles;

	private String htmlHelpText;

	protected boolean canBrowse = true;

	/**
	 * Creates a default information control with the given shell as parent. An
	 * information presenter that can handle simple HTML is used to process the
	 * information to be displayed.
	 *
	 * @param parent
	 *            the parent shell
	 * @param isResizeable
	 *            <code>true</code> if the control should be resizable
	 * @since 3.4
	 */

	/**
	 * Creates a default information control with the given shell as parent. An
	 * information presenter that can handle simple HTML is used to process the
	 * information to be displayed.
	 *
	 * @param parent
	 *            the parent shell
	 * @param statusFieldText
	 *            the text to be used in the status field or <code>null</code>
	 *            to hide the status field
	 * @since 3.4
	 */

	/**
	 * Creates a default information control with the given shell as parent. The
	 * given information presenter is used to process the information to be
	 * displayed.
	 *
	 * @param parent
	 *            the parent shell
	 * @param statusFieldText
	 *            the text to be used in the status field or <code>null</code>
	 *            to hide the status field
	 * @param presenter
	 *            the presenter to be used, or <code>null</code> if no presenter
	 *            should be used
	 * @since 3.4
	 */
	public RDefaultInformationControl(Shell parent, String statusFieldText, IInformationPresenter presenter) {
		super(parent, statusFieldText);
		fAdditionalTextStyles = SWT.NONE;
		fPresenter = presenter;
		create();
	}

	/**
	 * Creates a resizable default information control with the given shell as
	 * parent. An information presenter that can handle simple HTML is used to
	 * process the information to be displayed.
	 *
	 * @param parent
	 *            the parent shell
	 * @param toolBarManager
	 *            the manager or <code>null</code> if toolbar is not desired
	 * @since 3.4
	 */

	/**
	 * Creates a resizable default information control with the given shell as
	 * parent. The given information presenter is used to process the
	 * information to be displayed.
	 *
	 * @param parent
	 *            the parent shell
	 * @param toolBarManager
	 *            the manager or <code>null</code> if toolbar is not desired
	 * @param presenter
	 *            the presenter to be used, or <code>null</code> if no presenter
	 *            should be used
	 * @since 3.4
	 */
	public RDefaultInformationControl(Shell parent, ToolBarManager toolBarManager, IInformationPresenter presenter) {
		super(parent, toolBarManager);
		fAdditionalTextStyles = SWT.V_SCROLL | SWT.H_SCROLL;
		fPresenter = presenter;
		create();
	}

	/**
	 * Creates a default information control with the given shell as parent. No
	 * information presenter is used to process the information to be displayed.
	 *
	 * @param parent
	 *            the parent shell
	 */
	public RDefaultInformationControl(Shell parent) {
		this(parent, (String) null, null);
	}

	/**
	 * Creates a default information control with the given shell as parent. The
	 * given information presenter is used to process the information to be
	 * displayed.
	 *
	 * @param parent
	 *            the parent shell
	 * @param presenter
	 *            the presenter to be used
	 */
	public RDefaultInformationControl(Shell parent, IInformationPresenter presenter) {
		this(parent, (String) null, presenter);
	}

	/**
	 * Creates a default information control with the given shell as parent. The
	 * given information presenter is used to process the information to be
	 * displayed. The given styles are applied to the created styled text
	 * widget.
	 *
	 * @param parent
	 *            the parent shell
	 * @param shellStyle
	 *            the additional styles for the shell
	 * @param style
	 *            the additional styles for the styled text widget
	 * @param presenter
	 *            the presenter to be used
	 * @deprecated As of 3.4, replaced by simpler constructors
	 */

	/**
	 * Creates a default information control with the given shell as parent. The
	 * given information presenter is used to process the information to be
	 * displayed. The given styles are applied to the created styled text
	 * widget.
	 *
	 * @param parentShell
	 *            the parent shell
	 * @param shellStyle
	 *            the additional styles for the shell
	 * @param style
	 *            the additional styles for the styled text widget
	 * @param presenter
	 *            the presenter to be used
	 * @param statusFieldText
	 *            the text to be used in the status field or <code>null</code>
	 *            to hide the status field
	 * @since 3.0
	 * @deprecated As of 3.4, replaced by simpler constructors
	 */

	/**
	 * Creates a default information control with the given shell as parent. The
	 * given information presenter is used to process the information to be
	 * displayed.
	 *
	 * @param parent
	 *            the parent shell
	 * @param textStyles
	 *            the additional styles for the styled text widget
	 * @param presenter
	 *            the presenter to be used
	 * @deprecated As of 3.4, replaced by
	 *             {@link #DefaultInformationControl(Shell, RDefaultInformationControl.IInformationPresenter)}
	 */
	public RDefaultInformationControl(Shell parent, int textStyles, IInformationPresenter presenter) {
		this(parent, textStyles, presenter, null);
	}

	/**
	 * Creates a default information control with the given shell as parent. The
	 * given information presenter is used to process the information to be
	 * displayed.
	 *
	 * @param parent
	 *            the parent shell
	 * @param textStyles
	 *            the additional styles for the styled text widget
	 * @param presenter
	 *            the presenter to be used
	 * @param statusFieldText
	 *            the text to be used in the status field or <code>null</code>
	 *            to hide the status field
	 * @since 3.0
	 * @deprecated As of 3.4, replaced by
	 *             {@link #DefaultInformationControl(Shell, String, RDefaultInformationControl.IInformationPresenter)}
	 */
	public RDefaultInformationControl(Shell parent, int textStyles, IInformationPresenter presenter, String statusFieldText) {
		super(parent, statusFieldText);
		fAdditionalTextStyles = textStyles;
		fPresenter = presenter;
		create();
	}

	/*
	 * @see
	 * org.eclipse.jface.text.AbstractInformationControl#createContent(org.eclipse
	 * .swt.widgets.Composite)
	 */
	protected void createContent(Composite parent) {

		fText = new StyledText(parent, SWT.MULTI | SWT.READ_ONLY | fAdditionalTextStyles);
		fText.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.button == 3) {
					Job job = new Job("Html help") {
						private String url;

						@Override
						protected IStatus run(IProgressMonitor monitor) {
							monitor.beginTask("Help ...", IProgressMonitor.UNKNOWN);

							try {
								RConnection c = REditor.getRserveConnection();
								if (c != null) {
									if (RState.isBusy() == false) {
										RState.setBusy(true);
										Display display = openBrowser();

										htmlHelpText = RConfiguration.htmlHelpText;

										c.eval("try(outfile <- paste(tempfile(), \".html\", sep=\"\"))").toString();
										c.eval("try(tools::Rd2HTML(utils:::.getHelpFile(?" + htmlHelpText + "),outfile,package=\"tools\", stages=c(\"install\", \"render\")))");
										String out = null;
										try {
											out = (String) c.eval("try(outfile)").asString();
										} catch (REXPMismatchException e) {

											e.printStackTrace();
										}

										String pattern = "file:///" + out;
										url = pattern.replace("\\", "/");

										display.asyncExec(new Runnable() {

											public void run() {
												BrowserView b = BrowserView.getBrowserInstance();
												try {
													b.setLocation(url);
												} catch (Exception e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}
											}
										});
									}
									else{
										System.out.println("Rserve is busy!");
									}
								}

							} catch (RserveException e1) {

								e1.printStackTrace();
							}

							monitor.done();
							return Status.OK_STATUS;
						}

					};
					job.addJobChangeListener(new JobChangeAdapter() {
						public void done(IJobChangeEvent event) {
							if (event.getResult().isOK()) {

								RState.setBusy(false);
							} else {

							}
						}
					});
					// job.setSystem(true);
					job.schedule();
				} else if (event.button == 2) {

					if (canBrowse) {

						Job job = new Job("Html help") {
							private String url;

							@Override
							protected IStatus run(IProgressMonitor monitor) {
								monitor.beginTask("Help ...", IProgressMonitor.UNKNOWN);
								canBrowse = false;
								Display display = openBrowser();

								htmlHelpText = RConfiguration.htmlHelpText;

								display.asyncExec(new Runnable() {

									public void run() {
										BrowserView b = BrowserView.getBrowserInstance();
										try {
											b.setLocation("http://www.rdocumentation.org" + "#" + htmlHelpText);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});

								monitor.done();
								return Status.OK_STATUS;
							}

						};
						job.addJobChangeListener(new JobChangeAdapter() {
							public void done(IJobChangeEvent event) {
								if (event.getResult().isOK()) {
									canBrowse = true;
								} else {

								}
							}
						});
						// job.setSystem(true);
						job.schedule();
					}
				}
			}

		});
		fText.setForeground(parent.getForeground());
		fText.setBackground(parent.getBackground());
		fText.setFont(JFaceResources.getDialogFont());
		FillLayout layout = (FillLayout) parent.getLayout();
		if (fText.getWordWrap()) {
			// indent does not work for wrapping StyledText, see
			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=56342 and
			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=115432
			layout.marginHeight = INNER_BORDER;
			layout.marginWidth = INNER_BORDER;
		} else {
			fText.setIndent(INNER_BORDER);
		}
	}

	private Display openBrowser() {
		Display display = PlatformUI.getWorkbench().getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				try {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					page.showView("com.eco.bio7.browser.Browser");
				} catch (PartInitException e) {
					// TODO Auto-generated catch
					// block
					e.printStackTrace();
				}

			}
		});
		return display;
	}

	/*
	 * @see IInformationControl#setInformation(String)
	 */
	public void setInformation(String content) {

		if (fPresenter == null) {
			fText.setText(content);
		} else {
			fPresentation.clear();

			int maxWidth = -1;
			int maxHeight = -1;
			Point constraints = getSizeConstraints();
			if (constraints != null) {
				maxWidth = constraints.x;
				maxHeight = constraints.y;
				if (fText.getWordWrap()) {
					maxWidth -= INNER_BORDER * 2;
					maxHeight -= INNER_BORDER * 2;
				} else {
					maxWidth -= INNER_BORDER; // indent
				}
				Rectangle trim = computeTrim();
				maxWidth -= trim.width;
				maxHeight -= trim.height;
				maxWidth -= fText.getCaret().getSize().x; // StyledText adds a
															// border at the end
															// of the line for
															// the caret.
			}
			if (isResizable())
				maxHeight = Integer.MAX_VALUE;

			if (fPresenter instanceof IInformationPresenterExtension)
				content = ((IInformationPresenterExtension) fPresenter).updatePresentation(fText, content, fPresentation, maxWidth, maxHeight);
			else
				content = fPresenter.updatePresentation(getShell().getDisplay(), content, fPresentation, maxWidth, maxHeight);

			if (content != null) {
				fText.setText(content);
				TextPresentation.applyTextPresentation(fPresentation, fText);
			} else {
				fText.setText(""); //$NON-NLS-1$
			}
		}
	}

	/*
	 * @see IInformationControl#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		if (visible) {
			if (fText.getWordWrap()) {
				Point currentSize = getShell().getSize();
				getShell().pack(true);
				Point newSize = getShell().getSize();
				if (newSize.x > currentSize.x || newSize.y > currentSize.y)
					setSize(currentSize.x, currentSize.y); // restore previous
															// size
			}
		}

		super.setVisible(visible);
	}

	/*
	 * @see IInformationControl#computeSizeHint()
	 */
	public Point computeSizeHint() {
		// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=117602
		int widthHint = SWT.DEFAULT;
		Point constraints = getSizeConstraints();
		if (constraints != null && fText.getWordWrap())
			widthHint = constraints.x;

		return getShell().computeSize(widthHint, SWT.DEFAULT, true);
	}

	/*
	 * @see org.eclipse.jface.text.AbstractInformationControl#computeTrim()
	 */
	public Rectangle computeTrim() {
		return Geometry.add(super.computeTrim(), fText.computeTrim(0, 0, 0, 0));
	}

	/*
	 * @see IInformationControl#setForegroundColor(Color)
	 */
	public void setForegroundColor(Color foreground) {
		super.setForegroundColor(foreground);
		fText.setForeground(foreground);
	}

	/*
	 * @see IInformationControl#setBackgroundColor(Color)
	 */
	public void setBackgroundColor(Color background) {
		super.setBackgroundColor(background);
		fText.setBackground(background);
	}

	/*
	 * @see IInformationControlExtension#hasContents()
	 */
	public boolean hasContents() {
		return fText.getCharCount() > 0;
	}

	/**
	 * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
	 * @since 3.0
	 * @deprecated As of 3.2, no longer used and called
	 */
	public void widgetDisposed(DisposeEvent event) {
	}

	/*
	 * @see org.eclipse.jface.text.IInformationControlExtension5#
	 * getInformationPresenterControlCreator()
	 * 
	 * @since 3.4
	 */
	public IInformationControlCreator getInformationPresenterControlCreator() {
		return new IInformationControlCreator() {
			/*
			 * @see org.eclipse.jface.text.IInformationControlCreator#
			 * createInformationControl(org.eclipse.swt.widgets.Shell)
			 */
			public IInformationControl createInformationControl(Shell parent) {
				return new RDefaultInformationControl(parent, (ToolBarManager) null, fPresenter);
			}
		};
	}

}