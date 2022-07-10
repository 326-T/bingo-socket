import React from 'react';
type Props = {
  children?: React.ReactNode
};
const HomeLayout: React.FC<Props> = ({ children }) => {
  return(
    <div>
      <main>
        {children}
      </main>
    </div>
  );
}

export default HomeLayout;