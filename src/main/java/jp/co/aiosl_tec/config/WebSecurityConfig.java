package jp.co.aiosl_tec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity web) throws Exception {

		// 認証設定
		web.formLogin().loginPage("/login").failureUrl("/login?error=true") // ログイン失敗時エラーtrue
				.usernameParameter("user_id") // ユーザーIDのパラメータ名
				.passwordParameter("password") // パスワードのパラメータ名
				.defaultSuccessUrl("/menu") // 認証成功時の遷移先
				.permitAll().and();

		// ログアウト（GETでログアウトするためlogoutRequestMatcherを使用）
		web.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();

		// 静的リソース(css、js)に対するアクセスはセキュリティ設定を無視する
		web.authorizeRequests().antMatchers("/css/*","/js/*").permitAll().anyRequest().authenticated();
	}

	/*
	 * メモリ上にユーザー・パスワードを格納する処理
	 * ユーザーID「user」、パスワード「pass」が入力されたらログイン可能とする
	 * パスワードエンコーダーを利用しないようにするため、パスワードの先頭に{noop}を 指定している
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}pass").roles("USER");
	}
}
