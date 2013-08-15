package net.slipp.support.web.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.slipp.support.utils.SlippStringUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

public class PagerTag extends SimpleTagSupport {
	@SuppressWarnings("rawtypes")
	private Page page;

	/**
	 * 소팅 순서를 결정하는 값
	 */
	private String sortType;

	/**
	 * 페이징 대상의 기본 URL. 이 뒤에 페이징 관련 파라미터가 붙게 된다. 이 파라미터를 생략하면 URL생성및 {url} 값 대체
	 * 과정을 수행하지 않게 된다. Javascript로 URL처리를 하고 싶을 경우 생략하면 된다.
	 */
	private String prefixURI;

	private String suffixURI;

	/**
	 * WebSearch객체의 pageGroupSize 값을 대체한다. 0 이하이면 WebSearch 객체의 pageGroupSize를
	 * 사용한다.
	 */
	int pageGroupSize = -1;

	/** 실제 페이지 목록 전체를 감싸는 HTML의 시작 부분. 이전/다음 그룹과 이전/다음 페이지는 이 속에 속하지 않게 된다. */
	private String pagesWrapHtmlHead;

	/** 실제 페이지 목록 전체를 감싸는 HTML의 끝 부분. 이전/다음 그룹과 이전/다음 페이지는 이 속에 속하지 않게 된다. */
	private String pagesWrapHtmlTail;

	/** 이전 그룹 HTML {page}, {url} 지정 가능 */
	private String prevGroupHtml;

	/** 다음 그룹 HTML {page}, {url} 지정 가능 */
	private String nextGroupHtml;

	/** 일반 페이지 HTML. 필수 값이며, 등록하지 않으면 오류가 발생한다. {page}, {url} 지정 가능 */
	private String pageHtml;

	/** 현재 페이지 HTML. 이 값을 지정하지 않으면 pageHtml 값을 사용한다. {page}, {url} 지정 가능 */
	private String currentPageHtml;

	/** 페이지와 페이지 사이에 넣을 구분자. 없어도 무관하다. */
	private String pageSeparator;

	public void setPage(@SuppressWarnings("rawtypes") Page page) {
		this.page = page;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public void setPrefixURI(String prefixURI) {
		this.prefixURI = prefixURI;
	}

	public void setSuffixURI(String suffixURI) {
		this.suffixURI = suffixURI;
	}

	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}

	public void setPagesWrapHtmlHead(String pagesWrapHtmlHead) {
		this.pagesWrapHtmlHead = pagesWrapHtmlHead;
	}

	public void setPagesWrapHtmlTail(String pagesWrapHtmlTail) {
		this.pagesWrapHtmlTail = pagesWrapHtmlTail;
	}

	public void setPrevGroupHtml(String prevGroupHtml) {
		this.prevGroupHtml = prevGroupHtml;
	}

	public void setNextGroupHtml(String nextGroupHtml) {
		this.nextGroupHtml = nextGroupHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

	public void setCurrentPageHtml(String currentPageHtml) {
		this.currentPageHtml = currentPageHtml;
	}

	public void setPageSeparator(String pageSeparator) {
		this.pageSeparator = pageSeparator;
	}

	@Override
	public void doTag() throws JspException, IOException {
		Assert.notNull(page, "page를 지정해야 합니다.");
		Assert.isTrue(page.getTotalElements() >= 0, "totalCount는 0 이상이어야 합니다. 현재 값 : " + page.getTotalElements());
		Assert.isTrue(SlippStringUtils.isNotBlank(pageHtml), "pageHtml을 공백 이외의 문자열로 지정해야 합니다.");

		String html = generateEntireHtml();
		writeHtml(html);
	}

	protected String generateEntireHtml() {
		int currentPage = getCurrentPage();
		int finalPageGroupSize = pageGroupSize;
		int totalPage = page.getTotalPages();
		int startPageOfGroup = calculateStartPageOfGroup(currentPage, finalPageGroupSize);
		int endPageOfGroup = calculateEndPageOfGroup(currentPage, totalPage, finalPageGroupSize);
		Integer endPageOfPrevGroup = calculateEndPageOfPrevGroup(startPageOfGroup);
		Integer startPageOfNextGroup = calculateStartPageOfNextGroup(endPageOfGroup, totalPage);

		List<Integer> pagesOfGroup = generatePagesOfGroup(startPageOfGroup, endPageOfGroup);

		StringBuilder htmlBuilder = new StringBuilder();

		htmlBuilder.append(generatePrevGroupHtml(endPageOfPrevGroup));

		htmlBuilder.append(generatePageHtml(pagesOfGroup));

		htmlBuilder.append(generateNextGroupHtml(startPageOfNextGroup));

		return htmlBuilder.toString();
	}

	protected void writeHtml(String html) throws IOException {
		JspWriter writer = getJspContext().getOut();
		writer.write(html);
	}

	/**
	 * 현재 페이지 그룹의 시작 페이지 번호를 구한다
	 * 
	 * @param curentPage
	 * @param pageGroupSize
	 * @return
	 */
	protected int calculateStartPageOfGroup(int curentPage, int pageGroupSize) {
		int currentGroup = (curentPage - 1) / pageGroupSize;
		int startPage = (currentGroup * pageGroupSize) + 1;
		return startPage;
	}

	protected int calculateEndPageOfGroup(int curentPage, int totalPage, int pageGroupSize) {
		int currentGroup = (curentPage - 1) / pageGroupSize;
		int endPage = (currentGroup * pageGroupSize) + pageGroupSize;
		endPage = Math.min(totalPage, endPage);
		return endPage;
	}

	/**
	 * 이전 그룹의 마지막 페이지 번호를 리턴한다. null이면 앞 그룹이 없는 것이다.
	 * 
	 * @param 현재
	 *            그룹의 시작페이지 번호
	 * @return
	 */
	protected Integer calculateEndPageOfPrevGroup(int startPageOfGroup) {
		Integer endPageOfPrevGroup = startPageOfGroup - 1;
		if (endPageOfPrevGroup == 0) {
			endPageOfPrevGroup = null;
		}
		return endPageOfPrevGroup;
	}

	/**
	 * 다음 그룹의 마지막 시작 페이지 번호를 리턴한다. null 이면 다음 그룹이 없는 것이다.
	 * 
	 * @param endPageOfGroup
	 * @param totalPage
	 * @return
	 */
	protected Integer calculateStartPageOfNextGroup(int endPageOfGroup, int totalPage) {
		Integer startPageOfNextGroup = endPageOfGroup + 1;
		if (startPageOfNextGroup > totalPage) {
			startPageOfNextGroup = null;
		}
		return startPageOfNextGroup;
	}

	/**
	 * 현재 페이지 그룹의 페이지들을 순서대로 리스트로 생성한다.
	 * 
	 * @param startPageOfGroup
	 * @param endPageOfGroup
	 * @return 결코 null을 리턴하지 않는다.
	 */
	public List<Integer> generatePagesOfGroup(int startPageOfGroup, int endPageOfGroup) {
		List<Integer> pages = Lists.newArrayList();
		for (int currentPage = startPageOfGroup; currentPage <= endPageOfGroup; currentPage++) {
			pages.add(currentPage);
		}
		return pages;
	}

	/**
	 * {page}와 {url} 문자열을 실제 값으로 변환해준다.
	 * 
	 * @param string
	 * @param currentPage
	 * @param url
	 * @return
	 */
	protected String populateHtmlVariables(String string, int currentPage, String url) {
		if (SlippStringUtils.isEmpty(string)) {
			return string;
		}
		String populated = string.replaceAll("\\{page\\}", String.valueOf(currentPage));
		if (url != null) {
			populated = populated.replaceAll("\\{url\\}", SlippStringUtils.escapeHtml(url));
		}
		return populated;
	}

	/**
	 * 페이지 목록의 HTML을 생성한다.
	 * 
	 * @param pagesOfGroup
	 * @return
	 */
	protected String generatePageHtml(List<Integer> pagesOfGroup) {
		StringBuilder pageHtmlBuilder = new StringBuilder();

		appendPagesWrapHtmlHead(pageHtmlBuilder);

		for (int i = 0, size = pagesOfGroup.size(); i < size; i++) {
			int page = pagesOfGroup.get(i);
			appendPageHtml(page, pageHtmlBuilder);
			appendPageSeparator(pageHtmlBuilder, i, size);
		}

		appendPagesWrapHtmlTail(pageHtmlBuilder);
		return pageHtmlBuilder.toString();
	}

	private void appendPagesWrapHtmlHead(StringBuilder pageHtmlBuilder) {
		if (SlippStringUtils.isNotEmpty(pagesWrapHtmlHead)) {
			pageHtmlBuilder.append(pagesWrapHtmlHead);
		}
	}

	private void appendPagesWrapHtmlTail(StringBuilder pageHtmlBuilder) {
		if (SlippStringUtils.isNotEmpty(pagesWrapHtmlTail)) {
			pageHtmlBuilder.append(pagesWrapHtmlTail);
		}
	}

	private void appendPageHtml(int page, StringBuilder pageHtmlBuilder) {
		String url = parseUrl(prefixURI, page);

		String htmlToPopulate = getHtmlToPopulateForPage(page);

		String populatedPageHtml = populateHtmlVariables(htmlToPopulate, page, url);
		pageHtmlBuilder.append(populatedPageHtml);
	}

	private String getHtmlToPopulateForPage(int newPage) {
		String htmlToPopulate = pageHtml;
		boolean activePage = getCurrentPage() == newPage;
		if (activePage && SlippStringUtils.isNotEmpty(currentPageHtml)) {
			htmlToPopulate = currentPageHtml;
		}
		return htmlToPopulate;
	}

	private int getCurrentPage() {
		return page.getNumber() + 1;
	}

	private void appendPageSeparator(StringBuilder pageHtmlBuilder, int i, int size) {
		boolean needSeparator = SlippStringUtils.isNotEmpty(pageSeparator) && (i != (size - 1));

		if (needSeparator) {
			pageHtmlBuilder.append(pageSeparator);
		}
	}

	protected String generatePrevGroupHtml(Integer endPageOfPrevGroup) {
		return generateOptionalHtml(endPageOfPrevGroup, prevGroupHtml);
	}

	/**
	 * page값과 htmlTemplate의 존재여부에 따라 내용을 생성하거나 생성하지 않을 수 있는 HTML
	 * 
	 * @param page
	 *            null 이면 "" 리턴
	 * @param htmlTemplate
	 *            empty이면 "" 리턴
	 * @return
	 */
	private String generateOptionalHtml(Integer page, String htmlTemplate) {
		if (page == null) {
			return "";
		}

		if (SlippStringUtils.isEmpty(htmlTemplate)) {
			return "";
		}

		String url = parseUrl(prefixURI, page);
		String generatedHtml = populateHtmlVariables(htmlTemplate, page, url);

		return generatedHtml;
	}

	protected String generateNextGroupHtml(Integer startPageOfNextGroup) {
		return generateOptionalHtml(startPageOfNextGroup, nextGroupHtml);
	}

	protected String parseUrl(String prefixURI, int page) {
		if (StringUtils.isBlank(prefixURI)) {
			return prefixURI = null;
		}

		String paramConjunctionChar = prefixURI.contains("?") ? "&" : "?";
		StringBuffer urlBuffer = new StringBuffer();
		urlBuffer.append(prefixURI).append(paramConjunctionChar).append("page=").append(page);
		urlBuffer.append(suffixURI);

		if (!StringUtils.isBlank(sortType)) {
			urlBuffer.append("&sortType=");
			urlBuffer.append(sortType);
		}
		
		return urlBuffer.toString();
	}
}
