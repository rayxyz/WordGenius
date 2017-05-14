<#if pager??>
	<#-- number of button with number, the total buttons will be 'numed_page_btn + 2' -->
	<#assign numed_page_btns = 5 />
	<nav aria-label="Page navigation">
	  <ul class="pagination">
		<#if pager.pages lt (numed_page_btns + 2)>
		    <#list pager.pageNo..pager.pages as page_no_fact>
		    	<li page_no="${(page_no)!}"><a href="#">${(page_no)!}</a></li>
		    	<#assign page_no = pager.pageNo + page_no_fact />
		    </#list>
		    <#else>
	    		<#if pager.pageNo gt 1>
		    		<li page_no="${(pager.pageNo - 1)!}">
		    		<#else>
	    				<li page_no="${(pager.pageNo)!}">
				</#if>
				    <a href="#" aria-label="Previous" >
			            <span aria-hidden="true">&laquo;</span>
			        </a>
			    </li>
			    <#assign max_num = numed_page_btns />
			    <#if (max_num + pager.pageNo) lt pager.pages>
	    			<#assign max_num = max_num + pager.pageNo - 1 />
			    	<#else>
		    			<#assign max_num = pager.pages />
			    </#if>
				<#assign page_no = pager.pageNo />
				<#list pager.pageNo..max_num as page_no_fact>
			    	<li page_no="${(page_no_fact)!}"><a href="#">${(page_no_fact)!}</a></li>
			    </#list>
				<#assign next_page = pager.pageNo />
				<#if pager.pageNo lt pager.pages>
					<#assign next_page = next_page + 1 />
					<#else>
						<#assign next_page = pager.pages />
				</#if>
			    <li page_no="${(next_page)!}">
			      <a href="#" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
		</#if>
	  </ul>
	</nav>
</#if>