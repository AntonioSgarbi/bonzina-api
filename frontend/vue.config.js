module.exports = {
  lintOnSave: false
}
module.exports = {
  
  publicPath: process.env.NODE_ENV === 'production' ? '/bonzina-api/' : '/bonzina-api/',

  chainWebpack: config => {
    config.plugin('html').tap(args => {
      args[0].title = 'Clínica Médica';
      return args;
    });
  }
}