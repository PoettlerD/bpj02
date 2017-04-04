package at.campus02.bp2.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import at.campus02.bp2.model.Article;
import at.campus02.bp2.utils.EntityManagerProvider;

@ManagedBean
@SessionScoped
public class ArticleBean {

	private EntityManager entityManager = EntityManagerProvider.get();

	private Article newArticle = new Article();
	private List<Article> articleList = new ArrayList<Article>();
	
	public ArticleBean(){
	}
	
	public void loadArticlesFromDB() {
		articleList = entityManager.createQuery("from Article", Article.class).getResultList();
	}
	
	public void save() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(newArticle);
		transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Artikel " + newArticle.getName() + " wurde gespeichert"));
	}

	public List<Article> getArticleList() {
		loadArticlesFromDB();
		return articleList;
	}
	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	public Article getNewArticle() {
		return newArticle;
	}
	public void setNewArticle(Article newArticle) {
		this.newArticle = newArticle;
	}
}
