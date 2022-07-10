import React from 'react';
type Props = {
  children?: React.ReactNode
};
const HomeLayout: React.FC<Props> = ({ children }) => {
  return(
    <div>
      <h1>main</h1>
      <main>
        {children}
      </main>
    </div>
  );
}

export default HomeLayout;